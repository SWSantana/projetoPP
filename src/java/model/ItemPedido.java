/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author PICHAU
 */
public class ItemPedido {

    private int id;
    private Lote lote;
    private Movimento movimento;
    private int quantidade; 
    private double valorItem;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Lote getLote() {
        return lote;
    }
    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Movimento getMovimento() {
        return movimento;
    }
    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorItem() {
        return valorItem;
    }
    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }
    
    public static class ItemPedidoBuilder{
        ItemPedido itempedido = new ItemPedido();
        
        
        public ItemPedidoBuilder comId(int id) {
            itempedido.id = id;
            return this;
        }
        
        public ItemPedidoBuilder comLote(Lote lote) {
            itempedido.lote = lote;
        return this;
        }
        
        public ItemPedidoBuilder comMovimento(Movimento movimento) {
            itempedido.setMovimento(movimento);
        return this;
        }
        
        public ItemPedidoBuilder comQuantidade(int quantidade) {
            itempedido.quantidade = quantidade;
            return this;
        }
        
        public ItemPedidoBuilder comValorItem(Double valorItem) {
            itempedido.valorItem = valorItem;
            return this;
        }
        
        public ItemPedido constroi(){
            return itempedido;
        }
    }
}
