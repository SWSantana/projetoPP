/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PICHAU
 */
public class Notificacao {
    private int id;
    private int idLote;
    private String mensagem;
    private String numero;
    private String email;
  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    
    public String getNumero() {
        return numero;
    }

   
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public static class NotificacaoBuilder{
        Notificacao notificacao;
        
        public NotificacaoBuilder() {
            this.notificacao = new Notificacao();
        }
        public NotificacaoBuilder comIdLote(int idLote) {
            notificacao.idLote = idLote;
            return this;
        }
        
        public NotificacaoBuilder comMensagem(String mensagem) {
            notificacao.mensagem = mensagem;
            return this;
        }
        
        public NotificacaoBuilder comNumero(String numero) {
            notificacao.numero = numero;
            return this;
        }
        
        public NotificacaoBuilder comEmail(String email) {
            notificacao.email = email;
            return this;
        }
        public Notificacao constroi() {
            return notificacao;
        }
       
    }
    
}