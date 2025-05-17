package br.com.ifba.gdhortfrut.infrastructure.entity;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor //construtor com todos os atributos
@NoArgsConstructor //construtor vazio
public abstract class Pessoa extends PersistenceEntity{

    @NotBlank(message = "O nome eh obrigatorio")
    private String nome;
    @NotBlank(message = "O cpf eh obrigatorio")
    private String cpf;
    private String telefone;
}
