/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import model.Movimento;
import util.Conexao;
import java.time.LocalDateTime;
import java.time.LocalDate;
import extra.TipoMovimentacao;
import model.ItemPedido;
import model.Lote;

/**
 *
 * @author PICHAU
 */
public class MovimentoDAO {
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        return Conexao.getConexaoMySQL();
    }
    
    public void registrar(Movimento movimento, List<ItemPedido> itensPedido) throws SQLException, ClassNotFoundException {
    Connection conexao = getConexao();
    conexao.setAutoCommit(false); // impede criar movimento sem itempedido vinculado
    

    String sqlMovimento = "INSERT INTO movimento (tipo, valor, observacao, dataHora) VALUES (?, ?, ?, ?)";
    String sqlItem = "INSERT INTO itemPedido (id_movimento, id_lote, quantidade, valor_item) VALUES (?, ?, ?, ?)";
    
    String sinal = (movimento.getTipo() == TipoMovimentacao.ENTRADA) ? "+" : "-";
    String sqlLote = "UPDATE lote SET qtdLote = qtdLote " + sinal + " ?, dataAtualizacao = ? WHERE id = ?";

    try {
        int idMovimentoGerado = 0;

        // 1. Grava o movimento em si
        try (PreparedStatement comandoMovimento = conexao.prepareStatement(sqlMovimento, PreparedStatement.RETURN_GENERATED_KEYS)) {
            comandoMovimento.setString(1, movimento.getTipo().name()); 
            comandoMovimento.setDouble(2, movimento.getValor());
            comandoMovimento.setString(3, movimento.getObservacao());
            comandoMovimento.setObject(4, java.time.LocalDateTime.now()); 
            comandoMovimento.executeUpdate();

            try (ResultSet resultadoChaves = comandoMovimento.getGeneratedKeys()) {
                if (resultadoChaves.next()) {
                    idMovimentoGerado = resultadoChaves.getInt(1);
                }
            }
        }

        if (idMovimentoGerado == 0) {
            throw new SQLException("Falha ao registrar o cabeçalho da movimentação.");
        }

        // 2. Grava os itens usando a lista que veio por parâmetro
        for (ItemPedido item : itensPedido) {
            
            try (PreparedStatement comandoItem = conexao.prepareStatement(sqlItem)) {
                comandoItem.setInt(1, idMovimentoGerado);
                comandoItem.setInt(2, item.getLote().getId()); // Voltado para getId() do lote
                comandoItem.setInt(3, item.getQuantidade()); 
                comandoItem.setDouble(4, item.getValorItem());
                comandoItem.executeUpdate();
            }

            // 3. Atualiza o saldo do lote correspondente
            try (PreparedStatement comandoLote = conexao.prepareStatement(sqlLote)) {
                comandoLote.setInt(1, item.getQuantidade());
                comandoLote.setObject(2, java.time.LocalDate.now());
                comandoLote.setInt(3, item.getLote().getId());
                comandoLote.executeUpdate();
            }
        }

        conexao.commit(); 
        
    } catch (SQLException excecao) {
        if (conexao != null) {
            conexao.rollback(); 
        }
        throw excecao;
    } finally {
        if (conexao != null) {
            conexao.close(); 
        }
    }
}
    
    public List<ItemPedido> consultarItensMovimentados() throws ClassNotFoundException, SQLException {
        List<ItemPedido> listaItens = new ArrayList<>();

        String sql = "SELECT i.id, i.quantidade, i.valor_item, " +
                     "       m.id AS id_movimento, m.tipo, m.valor AS valor_movimento, m.observacao, m.dataHora, " +
                     "       l.id AS id_lote, l.qtdLote, l.preco, " +
                     "       p.id AS id_produto, p.descricao AS descricao_produto " +
                     "FROM itemPedido i " +
                     "INNER JOIN movimento m ON i.id_movimento = m.id " +
                     "INNER JOIN lote l ON i.id_lote = l.id " +
                     "INNER JOIN produto p ON l.id_produto = p.id";

        try (Connection conexao = getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                // Para os itens
                Produto produto = new Produto.ProdutoBuilder()
                    .comId(resultado.getInt("id_produto"))
                    .comDescricao(resultado.getString("descricao_produto"))
                    .constroi();

                // Instancia o Lote contendo o Produto via Builder
                Lote lote = new Lote.LoteBuilder()
                    .comId(resultado.getInt("id_lote"))
                    .comQtdLote(resultado.getInt("qtdLote"))
                    .comPreco(resultado.getDouble("preco"))
                    .comProduto(produto)
                    .constroi();

                // Instancia o Movimento pai via Builder
                Movimento movimentacao = new Movimento.MovimentoBuilder()
                    .comId(resultado.getInt("id_movimento"))
                    .comTipo(TipoMovimentacao.valueOf(resultado.getString("tipo")))
                    .comValor(resultado.getDouble("valor_movimento"))
                    .comObservacao(resultado.getString("observacao"))
                    .comDataHora(resultado.getTimestamp("dataHora").toLocalDateTime())
                    .constroi();

                // Monta o ItemPedido unindo todas as peças
                ItemPedido item = new ItemPedido.ItemPedidoBuilder()
                    .comId(resultado.getInt("id"))
                    .comQuantidade(resultado.getInt("quantidade"))
                    .comValorItem(resultado.getDouble("valor_item"))
                    .comLote(lote)
                    .comMovimento(movimentacao)
                    .constroi();

                listaItens.add(item);
            }
        }
        return listaItens;
    }
}