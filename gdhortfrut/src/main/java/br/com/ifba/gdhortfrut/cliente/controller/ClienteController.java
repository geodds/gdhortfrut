package br.com.ifba.gdhortfrut.cliente.controller;

import br.com.ifba.gdhortfrut.cliente.entity.Cliente;
import br.com.ifba.gdhortfrut.cliente.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    private ClienteService clienteService;

    @PutMapping
    public Cliente save( Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update( Cliente clienteAtualizado, Long id){
        return clienteService.update(clienteAtualizado, id);
    }

    @GetMapping("/{id}")
    public Cliente findById(Long id){
        return clienteService.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete( Long id){
        clienteService.delete(id);
    }
}
