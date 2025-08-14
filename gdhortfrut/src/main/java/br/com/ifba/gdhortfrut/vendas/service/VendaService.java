package br.com.ifba.gdhortfrut.vendas.service;

import br.com.ifba.gdhortfrut.cliente.entity.Cliente;
import br.com.ifba.gdhortfrut.cliente.repository.ClienteRepository;
import br.com.ifba.gdhortfrut.produto.entity.Produto;
import br.com.ifba.gdhortfrut.produto.repository.ProdutoRepository;
import br.com.ifba.gdhortfrut.vendas.dto.VendaRequest;
import br.com.ifba.gdhortfrut.vendas.entity.Venda;
import br.com.ifba.gdhortfrut.vendas.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendasRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public VendaService(VendaRepository vendasRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.vendasRepository = vendasRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Venda save(VendaRequest request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto nao econtrado."));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));

        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setCliente(cliente);
        venda.setQuantidade(request.getQuantidade());

        //calculando valor total
        double valorTotal = produto.getPreco() * request.getQuantidade();
        venda.setValorTotal(valorTotal);
        return vendasRepository.save(venda);
    }

    public Venda update(Long id, VendaRequest request) {
        Venda vendaExistente = vendasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda nao econtrada."));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado."));

        //buscando produto do banco
        Produto produto = produtoRepository.findById(request.getProdutoId())
                        .orElseThrow(() -> new EntityNotFoundException("Produto nao econtrado."));

        vendaExistente.setProduto(produto);
        vendaExistente.setCliente(cliente);
        vendaExistente.setQuantidade(request.getQuantidade());
        vendaExistente.setValorTotal(produto.getPreco() * request.getQuantidade());

        return vendasRepository.save(vendaExistente);
    }

    public void delete(Long id) {
        vendasRepository.deleteById(id);
    }

    public Optional<Venda> findById(Long id) {
        return vendasRepository.findById(id);
    }

    public List<Venda> findAll() {
        return vendasRepository.findAll();
    }
}
