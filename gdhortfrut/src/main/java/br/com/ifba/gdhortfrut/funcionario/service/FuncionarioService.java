package br.com.ifba.gdhortfrut.funcionario.service;

import br.com.ifba.gdhortfrut.funcionario.entity.Funcionario;
import br.com.ifba.gdhortfrut.funcionario.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario save(Funcionario funcionario){
        return repository.save(funcionario);
    }

    public Funcionario update(Funcionario funcionarioAtualizado, Long id){
        Funcionario funcionario = repository.findById(id).orElseThrow();
        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setCpf(funcionarioAtualizado.getCpf());
        funcionario.setFuncao(funcionarioAtualizado.getFuncao());
        return repository.save(funcionario);
    }

    public void delete(Long id){
        funcionarioRepository.deleteById(id);
    }

    public Optional<Funcionario> findById(Long id){
        return funcionarioRepository.findById(id);
    }

    public List<Funcionario> findAll(){
        return funcionarioRepository.findAll();
    }
}
