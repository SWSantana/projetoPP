/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.controller;

import dao.LoteDAO;
import java.sql.SQLException;
//import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lote;


/**
 *
 * @author PICHAU
 */
public class ConsultaByIdAction implements ICommand{
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoteDAO loteDAO = new LoteDAO();
            try {
                int id = Integer.parseInt(request.getParameter("idProduto"));
                Lote lote = loteDAO.consultarById(id);
                request.setAttribute("lote", lote);
            } catch (SQLException | NumberFormatException ex) {
                String msg = "Erro ao consultar";
                request.setAttribute("msg", msg);
            }
        
        return "resultadoconsultarbyid.jsp";
    }
 
    
}
