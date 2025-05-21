package br.com.ifba.gdhortfrut.produto.entity;

import br.com.ifba.gdhortfrut.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Produto extends PersistenceEntity {

    private String nome;
    private String descricao;
    private double preco;
}
