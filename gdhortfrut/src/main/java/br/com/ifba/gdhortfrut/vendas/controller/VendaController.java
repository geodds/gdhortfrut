package br.com.ifba.gdhortfrut.vendas.controller;

import br.com.ifba.gdhortfrut.produto.entity.Produto;
import br.com.ifba.gdhortfrut.produto.repository.ProdutoRepository;
import br.com.ifba.gdhortfrut.vendas.dto.VendaRequest;
import br.com.ifba.gdhortfrut.vendas.entity.Venda;
import br.com.ifba.gdhortfrut.vendas.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService service;
    private final ProdutoRepository produtoRepository;
    private final VendaService vendaService;

    public VendaController(VendaService service, ProdutoRepository produtoRepository, VendaService vendaService) {
        this.service = service;
        this.produtoRepository = produtoRepository;
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Venda> save(@RequestBody VendaRequest request) {
        Venda venda = vendaService.save(request);
        return new ResponseEntity<>(venda, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@PathVariable Long id, @RequestBody VendaRequest request) {
        //venda.setId(id);
        Venda vendaAtualizada = service.update(id, request);
        return ResponseEntity.ok(vendaAtualizada);
    }

    @GetMapping("/{id}")
    public Venda findById(@PathVariable Long id) {
        return service.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Venda> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/calcular")
    public double calcularValorTotal(
            @RequestParam Long produtoId,
            @RequestParam int quantidade
    ) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return produto.getPreco() * quantidade;
    }
}
