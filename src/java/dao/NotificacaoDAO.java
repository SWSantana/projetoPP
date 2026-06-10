/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Notificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.Conexao;

/**
 *
 * @author PICHAU
 */
public class NotificacaoDAO {
    
    public void salvar(Notificacao notificacao) throws ClassNotFoundException, SQLException {
        
        try (Connection conexao = Conexao.getConexaoMySQL()) {
             PreparedStatement comando = conexao.prepareStatement("INSERT INTO notificacao (id_lote, mensagem, numero, email) VALUES (?, ?, ?, ?)");
             comando.setInt(1, notificacao.getIdLote());
             comando.setString(2, notificacao.getMensagem());
             comando.setString(3, notificacao.getNumero());
             comando.setString(4, notificacao.getEmail());

             comando.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
