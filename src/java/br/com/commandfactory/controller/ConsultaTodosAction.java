/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.controller;
import dao.LoteDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lote;


/**
 *
 * @author PICHAU
 */
public class ConsultaTodosAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoteDAO ldao = new LoteDAO();
            List<Lote> llote = ldao.consultarTodos();
            request.setAttribute("llote", llote);
        
        return "resultadoconsultartodos.jsp";
    }
    
}
