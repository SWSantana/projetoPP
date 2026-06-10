/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.controller;

import dao.ProdutoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import dao.LoteDAO;
import java.time.LocalDate;
import model.Lote;

/**
 *
 * @author PICHAU
 */
public class EditaAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProdutoDAO produtoDao = new ProdutoDAO();
        LoteDAO loteDao = new LoteDAO();
        
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        
        Lote loteAtual = loteDao.consultarById(idProduto); 
        if (loteAtual == null) {
            return "index.html?erro=produto_nao_encontrado";
        }
            
        String novaDescricao = request.getParameter("descricaoProduto");
        String novaCategoria = request.getParameter("categoriaProduto");
        String novaQuantidadeMinimaStr = request.getParameter("quantidadeMinima");

        Produto produtoAlterado = new Produto.ProdutoBuilder()
            .comId(idProduto)
            .comDescricao((novaDescricao != null && !novaDescricao.trim().isEmpty()) ? novaDescricao : loteAtual.getProduto().getDescricao())
            .comCategoria(novaCategoria != null && !novaCategoria.trim().isEmpty() ? novaCategoria : loteAtual.getProduto().getCategoria())
            .comQtdMinima((novaQuantidadeMinimaStr != null && !novaQuantidadeMinimaStr.trim().isEmpty()) ? Integer.parseInt(novaQuantidadeMinimaStr) : loteAtual.getProduto().getQtdMinima())
            .constroi();
        
        String novoPrecoStr = request.getParameter("precoLote");               
        String novaQuantidadeLoteStr = request.getParameter("quantidadeLote"); 
        String novaDataValidadeStr = request.getParameter("dataValidade"); 

        Lote loteAlterado = new Lote.LoteBuilder()
            .comId(loteAtual.getId())
            .comProduto(produtoAlterado)
            .comPreco((novoPrecoStr != null && !novoPrecoStr.trim().isEmpty()) ? Double.parseDouble(novoPrecoStr) : loteAtual.getPreco())
            .comQtdLote((novaQuantidadeLoteStr != null && !novaQuantidadeLoteStr.trim().isEmpty()) ? Integer.parseInt(novaQuantidadeLoteStr) : loteAtual.getQtdLote())
            .comDataValidade((novaDataValidadeStr != null && !novaDataValidadeStr.trim().isEmpty()) ? LocalDate.parse(novaDataValidadeStr) : loteAtual.getDataValidade())
            .constroi();
        
        // Cada DAO cuida da sua própria vida e conexão aqui:
        produtoDao.atualizarCadastro(produtoAlterado);
        loteDao.atualizarCadastro(loteAlterado);
        
        return "index.html?editado=true";       
    }
    
}