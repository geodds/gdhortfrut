package br.com.ifba.gdhortfrut.vendas.dto;

import br.com.ifba.gdhortfrut.infrastructure.entity.PersistenceEntity;
import lombok.Data;

@Data
public class VendasResponse extends PersistenceEntity {

    private Long produtoId;
    private String nomeProduto;
    private int quantidade;
    private double valorTotal;
}
