package br.com.ifba.gdhortfrut.vendas.repository;

import br.com.ifba.gdhortfrut.vendas.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
