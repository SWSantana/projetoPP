/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.controller;


import dao.LoteDAO;
import dao.MovimentoDAO;
import extra.TipoMovimentacao;
import java.util.ArrayList;
import java.util.List;
import model.Movimento;
import model.Produto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ItemPedido;
import model.Lote;

public class RegistrarMovimentoAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MovimentoDAO movimentoDao = new MovimentoDAO();
        LoteDAO loteDao = new LoteDAO();

        //Executa a consulta no LoteDAO usando o ID do Produto
        Lote loteCorrespondente = loteDao.consultarById(Integer.parseInt(request.getParameter("idProduto"))); 
        
       
        if (loteCorrespondente == null) {
            return "registrarMovimento.jsp?erro=lote_nao_encontrado";
        }
        
        //Cria o ItemPedido usando o seu padrão Builder básico
            ItemPedido item = new ItemPedido.ItemPedidoBuilder()
                .comLote(loteCorrespondente)
                .comQuantidade(Integer.parseInt(request.getParameter("quantidade")))
                .comValorItem(loteCorrespondente.getPreco())
                .constroi();
            
            List<ItemPedido> listaDeItens = new ArrayList<>();
            listaDeItens.add(item);

            // 4. Calcula o valor total chamando o método polimórfico (o Decorator calcula se houver taxa/desconto)
            double valorTotalMovimento = item.getValorItem() * item.getQuantidade();

            // 5. Cria o objeto principal Movimento usando o valor final calculado pelo Decorator
            Movimento movimento = new Movimento.MovimentoBuilder()
                .comTipo(TipoMovimentacao.valueOf(request.getParameter("selTipo")))
                .comValor(valorTotalMovimento)
                .comObservacao(request.getParameter("obs"))
                .constroi();
            
        // 6. Envia a capa e a lista unitária para o método transacional que corrigimos no DAO
        movimentoDao.registrar(movimento, listaDeItens);
        
        // Redireciona com sucesso
        return "index.html?movimento=true";
    }
}
