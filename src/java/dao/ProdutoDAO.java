/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import model.Produto;
import util.Conexao;

/**
 *
 * @author PICHAU
 */
public class ProdutoDAO {
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        return Conexao.getConexaoMySQL();
    }
    
    public int cadastrar(Produto prod) throws ClassNotFoundException, SQLException {
    try (Connection connection = getConexao()) {
        PreparedStatement comando = connection.prepareStatement("insert into produto (descricao, categoria, qtdMinima) values (?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
        comando.setString(1, prod.getDescricao());
        comando.setString(2, prod.getCategoria());
        comando.setInt(3, prod.getQtdMinima());
        comando.execute();

        // CORREÇÃO: Recupera o ID gerado automaticamente pelo banco
        try (ResultSet resultadoChaves = comando.getGeneratedKeys()) { 
            if (resultadoChaves.next()) {
                return resultadoChaves.getInt(1); // Retorna o ID do novo produto
            }
        }
    }
    return 0;
    
    }
    
    public void atualizarCadastro(Produto prod) throws ClassNotFoundException, SQLException {
        try (Connection con = getConexao()) {
            PreparedStatement comando = con.prepareStatement("update produto set descricao = ?, categoria = ?, qtdMinima = ? where id = ?");
            comando.setString(1, prod.getDescricao());
            comando.setString(2, prod.getCategoria());
            comando.setInt(3, prod.getQtdMinima());
            comando.setInt(4, prod.getId());
            comando.execute();
        }
        
    }
    
    public void excluirTotal(Produto prod) throws ClassNotFoundException, SQLException {
        
        try (Connection con = getConexao()) {
            con.setAutoCommit(false);
            
            try {
            // 1. Apaga o estoque
            PreparedStatement comando = con.prepareStatement("DELETE FROM lote WHERE id_produto = ?");
            comando.setInt(1, prod.getId());
            comando.executeUpdate();

            // 2. Apaga o produto
            PreparedStatement comando2 = con.prepareStatement("DELETE FROM produto WHERE id = ?");
            comando2.setInt(1, prod.getId());

            con.commit(); // Salva as alterações
            } catch (SQLException e) {
            con.rollback(); // Desfaz se der erro
            throw e;
        }
        }
    }
}
