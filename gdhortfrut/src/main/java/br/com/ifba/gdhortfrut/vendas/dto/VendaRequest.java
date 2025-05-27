package br.com.ifba.gdhortfrut.vendas.dto;

public class VendaRequest {
    private Long produtoId;
    private int quantidade;

    public VendaRequest() { }

    public VendaRequest(Long produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
