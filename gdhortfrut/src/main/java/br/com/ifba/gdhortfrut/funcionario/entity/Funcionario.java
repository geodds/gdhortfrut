package br.com.ifba.gdhortfrut.funcionario.entity;

import br.com.ifba.gdhortfrut.infrastructure.entity.Pessoa;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Funcionario extends Pessoa {

    private String funcao;
}
