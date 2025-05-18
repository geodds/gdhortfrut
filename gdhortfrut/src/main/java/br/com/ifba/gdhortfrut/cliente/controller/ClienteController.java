package br.com.ifba.gdhortfrut.cliente.controller;

import br.com.ifba.gdhortfrut.cliente.entity.Cliente;
import br.com.ifba.gdhortfrut.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@RequestBody Cliente clienteAtualizado, @PathVariable Long id){
        return clienteService.update(clienteAtualizado, id);
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable Long id){
        return clienteService.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clienteService.delete(id);
    }
}
