package br.com.ifba.gdhortfrut.cliente.service;

import br.com.ifba.gdhortfrut.cliente.entity.Cliente;
import br.com.ifba.gdhortfrut.cliente.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public Cliente save( Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente update( Cliente clienteAtualizado, Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setCpf(clienteAtualizado.getCpf());
        cliente.setTelefone( clienteAtualizado.getTelefone());
        return clienteRepository.save(clienteAtualizado);
    }

    public void delete( Long id){
        clienteRepository.deleteById(id);
    }

    public Optional<Cliente> findById(Long id){
        return clienteRepository.findById(id);
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }
}
