package br.com.ifba.gdhortfrut.cliente.entity;

import br.com.ifba.gdhortfrut.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends PersistenceEntity {

    @NotBlank(message = "O nome eh obrigatorio")
    private String nome;
    @NotBlank(message = "O cpf eh obrigatorio")
    private String cpf;
    private String telefone;
}
