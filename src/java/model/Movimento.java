 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDateTime;
import extra.TipoMovimentacao;
/**
 *
 * @author PICHAU
 */
public class Movimento {

    private int id;
    private Produto produto;
    private TipoMovimentacao tipo;
    private LocalDateTime dataHora;
    private String observacao;
    private double valor;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Produto getProduto() {
        return produto; 
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }
    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public static class MovimentoBuilder{
        Movimento mov = new Movimento();
        public MovimentoBuilder comId(int id) {
            mov.id = id;
            return this;
        }
        
        public MovimentoBuilder comProduto(Produto produto) {
        mov.produto = produto;
        return this;
        }
        
        public MovimentoBuilder comTipo(TipoMovimentacao tipo) {
        mov.tipo = tipo;
        return this;
        }
        
        
        public MovimentoBuilder comDataHora(LocalDateTime dataHora) {
            mov.dataHora = dataHora;
            return this;
        }
        
        public MovimentoBuilder comValor (Double valor) {
            mov.valor = valor;      
            return this;     
        }
        
        public MovimentoBuilder comObservacao (String observacao) {
            mov.observacao = observacao;      
            return this;     
        }
        
        public Movimento constroi(){
            return mov;
        }
    }
}
