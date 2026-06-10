/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.controller;

import dao.MovimentoDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ItemPedido;
//import model.Movimento;

/**
 *
 * @author PICHAU
 */
public class ConsultarMOVAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MovimentoDAO movimentoDao = new MovimentoDAO();
      
        // Calistenia: variáveis com nomes limpos e expressivos
        List<ItemPedido> listaItensMovimentados = movimentoDao.consultarItensMovimentados(); 

        request.setAttribute("listaItensMovimentados", listaItensMovimentados);
   
        return "historico.jsp";
    } 
}
