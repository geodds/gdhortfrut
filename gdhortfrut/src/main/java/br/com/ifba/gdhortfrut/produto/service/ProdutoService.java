package br.com.ifba.gdhortfrut.produto.service;

import br.com.ifba.gdhortfrut.produto.entity.Produto;
import br.com.ifba.gdhortfrut.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtosRepository;

    public ProdutoService(ProdutoRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public Produto save(Produto produtos) {
        return produtosRepository.save(produtos);
    }

    public Produto update(Produto produtoAtualizado, Long id) {
        Produto produto = produtosRepository.findById(id).orElseThrow();
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        return produtosRepository.save(produto);
    }

    public void delete(Long id) {
        produtosRepository.deleteById(id);
    }

    public Optional<Produto> findById(Long id) {
        return produtosRepository.findById(id);
    }

    public List<Produto> findAll() {
        return produtosRepository.findAll();
    }
}
