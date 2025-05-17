package br.com.ifba.gdhortfrut.funcionario.repository;

import br.com.ifba.gdhortfrut.funcionario.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
