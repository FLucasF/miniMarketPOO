package DTO;

public class ProdutoDTO {
    private String nome;
    private int quantidade;
    private double valor;

    public String toString() {
        return "Produto de nome: " +this.nome+
                " valor: R$" +this.valor+
                " quantidade: " +this.quantidade;
    }
    public ProdutoDTO() {
        this("",0,0);
    }

    public ProdutoDTO(String nome, int quantidade, double valor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getQuantidade() {
        return this.quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Double getValor() {
        return this.valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }

}
