package br.com.ifba.gdhortfrut.vendas.service;

import br.com.ifba.gdhortfrut.produto.entity.Produto;
import br.com.ifba.gdhortfrut.produto.repository.ProdutoRepository;
import br.com.ifba.gdhortfrut.vendas.dto.VendaRequest;
import br.com.ifba.gdhortfrut.vendas.entity.Venda;
import br.com.ifba.gdhortfrut.vendas.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendasRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendasRepository, ProdutoRepository produtoRepository) {
        this.vendasRepository = vendasRepository;
        this.produtoRepository = produtoRepository;
    }

    public Venda save(VendaRequest request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto nao econtrado."));

        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setQuantidade(request.getQuantidade());

        //venda.setProduto(produto);

       /* double preco = produto.getPreco();
        int quantidade = venda.getQuantidade();*/

        //calculando valor total
        double valorTotal = produto.getPreco() * request.getQuantidade();
        venda.setValorTotal(valorTotal);

        return vendasRepository.save(venda);
    }

    public Venda update(Long id, VendaRequest request) {
        /*if (venda.getId() == null) {
            throw new IllegalArgumentException("O ID da venda não pode ser nulo.");
        }

        Venda vendaExistente = vendasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda nao econtrada."));
*/

        Venda vendaExistente = vendasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda nao econtrada."));

        //buscando produto do banco
        Produto produto = produtoRepository.findById(request.getProdutoId())
                        .orElseThrow(() -> new EntityNotFoundException("Produto nao econtrado."));

        vendaExistente.setProduto(produto);
        vendaExistente.setQuantidade(request.getQuantidade());
        vendaExistente.setValorTotal(produto.getPreco() * request.getQuantidade());

        //atualizando valor total
        /*double valorTotalAtualizado = produtoAtualizado.getPreco() * vendaExistente.getQuantidade();
        vendaExistente.setValorTotal(valorTotalAtualizado);*/

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
