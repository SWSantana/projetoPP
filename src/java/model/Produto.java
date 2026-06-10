/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PICHAU
 */
public class Produto {

    private int id;
    private String descricao;
    private String categoria;

    private int qtdMinima;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }
    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }
    
    
    public static class ProdutoBuilder{
        Produto prod;
        
        public ProdutoBuilder() {
            this.prod = new Produto();
        }
        public ProdutoBuilder comId(int id) {
            prod.id = id;
            return this;
        }
    
        public ProdutoBuilder comDescricao(String descricao) {
            prod.descricao = descricao;
            return this;
        }
        
        public ProdutoBuilder comCategoria(String categoria) {
            prod.categoria = categoria;
            return this;
        }
      
        public ProdutoBuilder comQtdMinima(int qtdMinima) {
            prod.qtdMinima = qtdMinima;
            return this;
        }
        
        public Produto constroi() {
            return prod;
        }
       
    }
    
}

