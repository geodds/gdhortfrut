package br.com.ifba.gdhortfrut.vendas.dto;

import lombok.Getter;

@Getter
public class VendaRequest {

    private Long produtoId;
    private int quantidade;
    private Long clienteId;

    public VendaRequest() { }

    public VendaRequest(Long produtoId, int quantidade, Long clienteId) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.clienteId = clienteId;
    }

}
