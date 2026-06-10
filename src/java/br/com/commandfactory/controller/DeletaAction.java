package br.com.commandfactory.controller;

import dao.ProdutoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PICHAU
 */

public class DeletaAction implements ICommand {
@Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id = Integer.parseInt(request.getParameter("idProduto"));

        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setId(id);
        
        produtoDAO.excluirTotal(produto); 

        // Redireciona de volta para a index com um aviso de sucesso
        return "index.html?excluido=true";
    }
}

