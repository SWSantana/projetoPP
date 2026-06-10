
package model;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
/**
 *
 * @author PICHAU
 */
public class Lote {
    private int id;
    private Produto produto;
    private int qtdLote;
    private LocalDate dataAtualizacao;
    private LocalDate dataValidade;
    private double preco;
    
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
    
    public int getQtdLote() {
        return qtdLote;
    }
    public void setQtdLote(int qtdLote) {
        this.qtdLote = qtdLote;
    }
    
    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }


    
    public static class LoteBuilder{
        Lote lote;
        
        public LoteBuilder() {
            this.lote = new Lote();
        }
        
        public LoteBuilder comId(int id) {
            lote.id = id;
            return this;
        }
        
        public LoteBuilder comProduto(Produto produto) {
        lote.produto = produto;
        return this;
        }
        
        public LoteBuilder comQtdLote(int qtdLote) {
            lote.qtdLote = qtdLote;
            return this;
        }
        
        public LoteBuilder comDataAtualizacao(LocalDate dataAtualizacao) {
            lote.dataAtualizacao = dataAtualizacao;
            return this;
        }
        
        public LoteBuilder comDataValidade(LocalDate dataValidade) {
            lote.dataValidade = dataValidade;
            return this;
        }
        
        public LoteBuilder comPreco(double preco) {
            lote.preco = preco;
            return this;
        }
        
        public Lote constroi(){
            return lote;
        }
    }
    
}
