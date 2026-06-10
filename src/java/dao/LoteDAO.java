/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Lote;
import model.Produto;
import util.Conexao;
import java.time.LocalDate;


/**
 *
 * @author PICHAU
 */
public class LoteDAO {
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        return Conexao.getConexaoMySQL();
    }
    
    public int cadastrar(Lote lote) throws ClassNotFoundException, SQLException {
       try (Connection connection = getConexao()) {

           PreparedStatement comandoLote = connection.prepareStatement("insert into lote (id_produto, preco ,qtdLote, dataAtualizacao, dataValidade) values (?, ?, ?, ?, ?)", java.sql.Statement.RETURN_GENERATED_KEYS);
           comandoLote.setInt(1, lote.getProduto().getId());
           comandoLote.setDouble(2, lote.getPreco());
           comandoLote.setInt(3, lote.getQtdLote());
           comandoLote.setObject(4, java.time.LocalDate.now()); // Usando LocalDate.now para a data de atualização inicial
           comandoLote.setObject(5, lote.getDataValidade());
           comandoLote.execute();

           
           try (ResultSet resultadoChaves = comandoLote.getGeneratedKeys()) { 
                if (resultadoChaves.next()) {
                    return resultadoChaves.getInt(1); // Retorna o ID do novo lote
                }
            }
           
           }
        return 0;
       }
    
    public void atualizarCadastro(Lote lote) throws ClassNotFoundException, SQLException {
        try (Connection con = getConexao()) {
            PreparedStatement comando = con.prepareStatement("update lote set qtdLote = ?, dataAtualizacao = ?, dataValidade = ?, preco = ? where id_produto = ?");
            comando.setInt(1, lote.getQtdLote());
            comando.setObject(2, LocalDate.now());
            comando.setObject(3, lote.getDataValidade());
            comando.setDouble(4, lote.getPreco());
            comando.setInt(5, lote.getProduto().getId());
            comando.execute();
        }
        
    }
    
    
    public Lote consultarById(int id) throws ClassNotFoundException, SQLException {
        Connection conexao = getConexao();
        PreparedStatement comando = conexao.prepareStatement("SELECT l.id as ID_Estoque, p.id as ID_Produto, p.descricao as Descricao, p.categoria as Categoria, l.preco as Preco, p.qtdMinima as Quantidade_Minima, " +
                                                         " l.qtdLote as Quantidade_Lote, l.dataAtualizacao as Data_Atualizacao, l.dataValidade as Data_Validade FROM lote as l , produto as p" + 
                                                         " WHERE l.id_produto = p.id AND l.id_produto = ?;");
        comando.setInt(1, id);
        ResultSet rs = comando.executeQuery();      
        
        Lote lote = null;
        
        
        if (rs.next()) {
            lote = new Lote();  
            lote.setId(rs.getInt("ID_Estoque")); 
            lote.setQtdLote(rs.getInt("Quantidade_Lote"));
            lote.setDataAtualizacao(rs.getObject("Data_Atualizacao", java.time.LocalDate.class));
            lote.setDataValidade(rs.getObject("Data_Validade", java.time.LocalDate.class));
            lote.setPreco(rs.getDouble("Preco"));
            //Preenche o produto do lote
            Produto produto = new Produto();
            produto.setId(rs.getInt("ID_Produto")); 
            produto.setDescricao(rs.getString("Descricao"));
            produto.setCategoria(rs.getString("Categoria"));
            produto.setQtdMinima(rs.getInt("Quantidade_Minima"));
            
            lote.setProduto(produto); 
        } else{
            lote = null;
        }      
        conexao.close();
        return lote;
    }
 
    public List<Lote> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection conexao = getConexao();
        PreparedStatement comando = conexao.prepareStatement("SELECT l.id as ID_Estoque, p.id as ID_Produto, p.descricao as Descricao, p.categoria as Categoria, p.qtdMinima as Quantidade_Minima, l.preco as Preco ," +
                                                         " l.qtdLote as Quantidade_Lote, l.dataAtualizacao as Data_Atualizacao, l.dataValidade as Data_Validade FROM lote as l , produto as p WHERE l.id_produto = p.id;");        
        ResultSet rs = comando.executeQuery();   
        Lote lote = null;
        List<Lote> llote = new ArrayList<Lote>();
        int cont = 0;
        while(rs.next()){
            lote = new Lote();  
            lote.setId(rs.getInt("ID_Estoque")); 
            lote.setPreco(rs.getDouble("Preco"));
            lote.setQtdLote(rs.getInt("Quantidade_Lote"));
            lote.setDataAtualizacao(rs.getObject("Data_Atualizacao", java.time.LocalDate.class));
            lote.setDataValidade(rs.getObject("Data_Validade", java.time.LocalDate.class));//PRECISA ADICIONAR ESTE INPUT
            
            //Preenche o produto do lote
            Produto prod = new Produto();
            prod.setId(rs.getInt("ID_Produto")); 
            prod.setDescricao(rs.getString("Descricao"));
            prod.setCategoria(rs.getString("Categoria"));
            prod.setQtdMinima(rs.getInt("Quantidade_Minima"));
            
            lote.setProduto(prod);
            llote.add(lote);
            cont++;
        }
        System.out.println("Cont..: " + cont);
        conexao.close();
        return llote;
    }
    
    
}

