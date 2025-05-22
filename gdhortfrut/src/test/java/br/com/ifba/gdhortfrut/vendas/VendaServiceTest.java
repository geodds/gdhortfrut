package br.com.ifba.gdhortfrut.vendas;

import br.com.ifba.gdhortfrut.produto.entity.Produto;
import br.com.ifba.gdhortfrut.produto.repository.ProdutoRepository;
import br.com.ifba.gdhortfrut.vendas.entity.Venda;
import br.com.ifba.gdhortfrut.vendas.repository.VendaRepository;
import br.com.ifba.gdhortfrut.vendas.service.VendaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VendaServiceTest {

    private VendaRepository vendaRepository;
    private ProdutoRepository produtoRepository;
    private VendaService vendaService;

    @BeforeEach
    void setUp() {
        vendaRepository = mock(VendaRepository.class);
        produtoRepository = mock(ProdutoRepository.class);
        vendaService = new VendaService(vendaRepository, produtoRepository);
    }

    @Test
    void testSaveVendaComProdutoValido() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setPreco(10.0);

        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setQuantidade(3);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(vendaRepository.save(any(Venda.class))).thenAnswer(i -> i.getArgument(0));

        Venda savedVenda = vendaService.save(venda);

        assertEquals(20.0, savedVenda.getValorTotal());
        verify(vendaRepository).save(any(Venda.class));
    }

    @Test
    void testSaveVendaComProdutoInvalido() {
        Venda venda = new Venda();
        Produto produto = new Produto();
        produto.setId(99L);
        venda.setProduto(produto);
        venda.setQuantidade(2);

        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> vendaService.save(venda));
    }

    @Test
    void testUpdateVenda() {
        Produto produtoAntigo = new Produto();
        produtoAntigo.setId(1L);
        produtoAntigo.setPreco(5.0);

        Produto produtoNovo = new Produto();
        produtoNovo.setId(2L);
        produtoNovo.setPreco(8.0);

        Venda vendaAntiga = new Venda();
        vendaAntiga.setId(1L);
        vendaAntiga.setProduto(produtoAntigo);
        vendaAntiga.setQuantidade(2);
        vendaAntiga.setValorTotal(10.0);

        Venda vendaAtualizada = new Venda();
        vendaAtualizada.setId(1L);
        vendaAtualizada.setProduto(produtoNovo);
        vendaAtualizada.setQuantidade(4);

        when(vendaRepository.findById(1L)).thenReturn(Optional.of(vendaAntiga));
        when(produtoRepository.findById(2L)).thenReturn(Optional.of(produtoNovo));
        when(vendaRepository.save(any(Venda.class))).thenAnswer(i -> i.getArgument(0));

        Venda updated = vendaService.update(vendaAtualizada, 1L);

        assertEquals(produtoNovo, updated.getProduto());
        assertEquals(4, updated.getQuantidade());
        assertEquals(32.0, updated.getValorTotal());
    }

    @Test
    void testFindById() {
        Venda venda = new Venda();
        venda.setId(1L);
        when(vendaRepository.findById(1L)).thenReturn(Optional.of(venda));

        Optional<Venda> result = vendaService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindAll() {
        Venda v1 = new Venda();
        Venda v2 = new Venda();
        when(vendaRepository.findAll()).thenReturn(Arrays.asList(v1, v2));

        var list = vendaService.findAll();

        assertEquals(2, list.size());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        vendaService.delete(id);

        verify(vendaRepository, times(1)).deleteById(id);
    }
}
