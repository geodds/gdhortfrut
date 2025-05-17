package br.com.ifba.gdhortfrut.funcionario.controller;

import br.com.ifba.gdhortfrut.funcionario.entity.Funcionario;
import br.com.ifba.gdhortfrut.funcionario.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping("/{id}")
    public Funcionario save(Funcionario funcionario){
        return service.save(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario update(Funcionario funcionario, Long id){
        return service.update(funcionario, id);
    }

    @GetMapping("/{id}")
    public Funcionario findById(Long id){
        return service.findById(id).orElseThrow();
    }

    @GetMapping
    public Iterable<Funcionario> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        service.delete(id);
    }
}
