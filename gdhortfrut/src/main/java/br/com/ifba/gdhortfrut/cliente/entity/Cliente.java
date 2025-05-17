package br.com.ifba.gdhortfrut.cliente.entity;

import br.com.ifba.gdhortfrut.infrastructure.entity.PersistenceEntity;
import br.com.ifba.gdhortfrut.infrastructure.entity.Pessoa;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa {

}
