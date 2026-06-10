/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.controller;

import dao.ProdutoDAO;
import dao.LoteDAO;
import dao.NotificacaoDAO;
import decorator.Notificador;
import decorator.NotificadorEmail;
import decorator.SMSDecorator;
import decorator.TelegramDecorator;
import decorator.WhatsappDecorator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lote;
import model.Produto;
import java.time.LocalDate;
import model.Notificacao;

/**
 *
 * @author PICHAU
 */
public class CadastraAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            LoteDAO loteDao = new LoteDAO();
            
            Produto produto = new Produto.ProdutoBuilder() // Acessa a classe estática interna
                .comDescricao(request.getParameter("descricaoProduto"))
                .comCategoria(request.getParameter("categoriaProduto"))
                .comQtdMinima(Integer.parseInt(request.getParameter("quantidadeMinima")))
                .constroi(); // Só aqui o objeto 'Produto' é realmente entregue pronto

            
            // Salva e armazena o ID real gerado pelo MySQL
            int idGerado = produtoDAO.cadastrar(produto); 

            // Se falhou o ID, nem tenta o lote
            if (idGerado == 0) {
                return "index.html?erro=falha_ao_gerar_id";
            }
            
           
            // Injeta o ID correto de volta no objeto produto
            produto.setId(idGerado);
            
            Lote lote = new Lote.LoteBuilder()
                .comPreco(Double.parseDouble(request.getParameter("precoLote")))
                .comDataValidade(LocalDate.parse(request.getParameter("dataValidade")))
                .comQtdLote(Integer.parseInt(request.getParameter("quantidadeLote"))) 
                .comProduto(produto)
                .constroi();                        
            
            int idGeradoLote = loteDao.cadastrar(lote); 
            
            String emailInformado = request.getParameter("email");
            String querWhats      = request.getParameter("whatsapp");
            String querSMS        = request.getParameter("SMS");
            String querTelegram   = request.getParameter("telegram");
            String numeroTel      = request.getParameter("numero");
            Notificador notificador = new NotificadorEmail(emailInformado);

            if (querWhats != null) {
                notificador = new WhatsappDecorator(notificador, numeroTel);
            }
            if (querSMS != null) {
                notificador = new SMSDecorator(notificador, numeroTel);
            }
            if (querTelegram != null) {
                notificador = new TelegramDecorator(notificador, numeroTel);
            }

            // 3. O DECORATOR TRABALHA: Gera a string final (Ex: "E-mail enviado para X | WhatsApp enviado")
            String logMensagem = notificador.enviarNotificacao("Lote " + idGeradoLote + " cadastrado!");

            
            Notificacao Notificacao = new Notificacao.NotificacaoBuilder()
                .comIdLote(idGeradoLote)
                .comMensagem(logMensagem)
                .comEmail(emailInformado)
                .comNumero(numeroTel)
                .constroi();
            
            NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
            notificacaoDAO.salvar(Notificacao);

           String urlRetorno = "index.html?cadastrado=true&notificacao=" + java.net.URLEncoder.encode(logMensagem, "UTF-8");
           return urlRetorno;
        }
}
