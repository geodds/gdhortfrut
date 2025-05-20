package br.com.ifba.gdhortfrut.produto;

import br.com.ifba.gdhortfrut.produto.entity.Produto;
import br.com.ifba.gdhortfrut.produto.repository.ProdutoRepository;
import br.com.ifba.gdhortfrut.produto.service.ProdutoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceTest {

    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        produtoRepository = mock(ProdutoRepository.class);
        produtoService = new ProdutoService(produtoRepository);
    }

    @Test
    void testSalvarProduto() {
        Produto produto = new Produto();
        produto.setNome("Banana");
        produto.setDescricao("Banana prata");
        produto.setPreco(5.0);

        when(produtoRepository.save(produto)).thenReturn(produto);

        Produto resultado = produtoService.save(produto);

        assertNotNull(resultado);
        assertEquals("Banana", resultado.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testAtualizarProduto() {
        Produto produtoExistente = new Produto();
        produtoExistente.setId(1L);
        produtoExistente.setNome("Maçã");
        produtoExistente.setDescricao("Maçã verde");
        produtoExistente.setPreco(4.0);

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setNome("Maçã Fuji");
        produtoAtualizado.setDescricao("Maçã doce");
        produtoAtualizado.setPreco(6.0);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(ArgumentMatchers.any(Produto.class))).thenReturn(produtoExistente);

        Produto resultado = produtoService.update(produtoAtualizado, 1L);

        assertEquals("Maçã Fuji", resultado.getNome());
        assertEquals("Maçã doce", resultado.getDescricao());
        assertEquals(6.0, resultado.getPreco());
        verify(produtoRepository, times(1)).save(produtoExistente);
    }

    @Test
    void testExcluirProduto() {
        produtoService.delete(1L);
        verify(produtoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testBuscarProdutoPorId() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Laranja");

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> resultado = produtoService.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Laranja", resultado.get().getNome());
    }

    @Test
    void testBuscarTodosProdutos() {
        Produto p1 = new Produto();
        p1.setNome("Tomate");

        Produto p2 = new Produto();
        p2.setNome("Alface");

        List<Produto> produtos = Arrays.asList(p1, p2);

        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> resultado = produtoService.findAll();

        assertEquals(2, resultado.size());
        assertEquals("Tomate", resultado.get(0).getNome());
        assertEquals("Alface", resultado.get(1).getNome());
    }
}
