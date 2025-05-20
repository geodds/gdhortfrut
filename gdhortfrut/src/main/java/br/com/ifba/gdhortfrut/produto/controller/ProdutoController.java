package br.com.ifba.gdhortfrut.produto.controller;

import br.com.ifba.gdhortfrut.produto.entity.Produto;
import br.com.ifba.gdhortfrut.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public Produto save(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @GetMapping("/{id}")
    public Produto findById(@PathVariable Long id) {
        return produtoService.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Produto update(@RequestBody Produto produtoAtualizado, @PathVariable Long id) {
        return produtoService.update(produtoAtualizado, id);
    }

    @GetMapping
    public List<Produto> findAll() {
        return produtoService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produtoService.delete(id);
    }

}
