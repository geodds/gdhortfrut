package br.com.ifba.gdhortfrut.vendas.controller;

import br.com.ifba.gdhortfrut.vendas.entity.Venda;
import br.com.ifba.gdhortfrut.vendas.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    public Venda save(@RequestBody Venda venda) {
        return service.save(venda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@PathVariable Long id, @RequestBody Venda venda) {
        venda.setId(id);
        Venda vendaAtualizada = service.update(venda, id);
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
}
