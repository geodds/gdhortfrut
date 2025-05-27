package br.com.ifba.gdhortfrut.vendas;

import br.com.ifba.gdhortfrut.produto.entity.Produto;
import br.com.ifba.gdhortfrut.produto.repository.ProdutoRepository;
import br.com.ifba.gdhortfrut.vendas.dto.VendaRequest;
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

public class VendaTest {

    private VendaRepository vendaRepository;
    private ProdutoRepository produtoRepository;
    private VendaService vendaService;

    @BeforeEach
    void setUp() {
        // Cria mocks para os repositórios que serão usados pelo serviço
        vendaRepository = mock(VendaRepository.class);
        produtoRepository = mock(ProdutoRepository.class);
        // Instancia o serviço injetando os mocks, permitindo testar isoladamente
        vendaService = new VendaService(vendaRepository, produtoRepository);
    }

    @Test
    void testSaveVendaComProdutoValido() {
        // Cria um produto válido com preço definido
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setPreco(10.0);

        VendaRequest venda = new VendaRequest(1L, 2);

        /*/ Cria uma venda associada a esse produto, com quantidade 3
        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setQuantidade(3);*/

        // Simula o retorno do produto existente no repositório de produtos
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        // Simula o salvamento da venda, retornando o próprio objeto salvo
        when(vendaRepository.save(any(Venda.class))).thenAnswer(i -> i.getArgument(0));

        // Executa o método save do serviço
        Venda savedVenda = vendaService.save(venda);

        // Verifica se o valor total da venda foi calculado corretamente (preço * quantidade)
        assertEquals(30.0, savedVenda.getValorTotal());
        // Garante que o método save do repositório de vendas foi chamado
        verify(vendaRepository).save(any(Venda.class));
    }

    @Test
    void testSaveVendaComProdutoInvalido() {
        /*/ Cria uma venda com um produto cujo ID não existe
        Venda venda = new Venda();
        Produto produto = new Produto();
        produto.setId(99L); // ID inexistente
        venda.setProduto(produto);
        venda.setQuantidade(2);*/

        VendaRequest venda = new VendaRequest(99L,2);

        // Simula o repositório de produtos não encontrar esse produto
        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        // Espera que salvar a venda lance uma exceção por produto inválido
        assertThrows(RuntimeException.class, () -> vendaService.save(venda));

        // Verifica que o método save do repository não foi chamado
        verify(vendaRepository, never()).save(any(Venda.class));
    }

    @Test
    void testUpdateVenda() {
        // Produto antigo com preço 5.0
        Produto produtoAntigo = new Produto();
        produtoAntigo.setId(1L);
        produtoAntigo.setPreco(5.0);

        // Produto novo com preço 8.0
        Produto produtoNovo = new Produto();
        produtoNovo.setId(2L);
        produtoNovo.setPreco(8.0);

        // Venda antiga ligada ao produto antigo, quantidade 2, valor total 10.0
        Venda vendaAntiga = new Venda();
        vendaAntiga.setId(1L);
        vendaAntiga.setProduto(produtoAntigo);
        vendaAntiga.setQuantidade(2);
        vendaAntiga.setValorTotal(10.0);

        VendaRequest venda = new VendaRequest(2L, 4);

        /*/ Venda com dados atualizados: produto novo e quantidade 4
        Venda vendaAtualizada = new Venda();
        vendaAtualizada.setId(1L);
        vendaAtualizada.setProduto(produtoNovo);
        vendaAtualizada.setQuantidade(4);*/

        // Simula buscas no repositório para produto e venda antigos/novos
        when(vendaRepository.findById(1L)).thenReturn(Optional.of(vendaAntiga));
        when(produtoRepository.findById(2L)).thenReturn(Optional.of(produtoNovo));
        // Simula salvamento da venda retornando o objeto atualizado
        when(vendaRepository.save(any(Venda.class))).thenAnswer(i -> i.getArgument(0));

        // Chama o método update no serviço com os dados atualizados
        Venda updated = vendaService.update(1L, venda);

        // Verifica se a venda atualizada tem o produto novo, quantidade e valor total corretos
        assertEquals(produtoNovo, updated.getProduto());
        assertEquals(4, updated.getQuantidade());
        assertEquals(32.0, updated.getValorTotal()); // 8.0 * 4
    }

    @Test
    void testFindById() {
        // Cria uma venda com ID 1
        Venda venda = new Venda();
        venda.setId(1L);
        // Simula o repositório encontrando essa venda pelo ID
        when(vendaRepository.findById(1L)).thenReturn(Optional.of(venda));

        // Busca a venda pelo serviço
        Optional<Venda> result = vendaService.findById(1L);

        // Verifica se a venda foi encontrada e se o ID está correto
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindAll() {
        // Cria duas vendas para simular a lista retornada
        Venda v1 = new Venda();
        Venda v2 = new Venda();
        // Simula o repositório retornando essa lista ao buscar todas as vendas
        when(vendaRepository.findAll()).thenReturn(Arrays.asList(v1, v2));

        // Chama o método para obter todas as vendas
        var list = vendaService.findAll();

        // Verifica se o tamanho da lista retornada é 2, conforme esperado
        assertEquals(2, list.size());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        // Chama o método delete do serviço para remover uma venda pelo ID
        vendaService.delete(id);

        // Verifica se o método deleteById do repositório foi chamado uma vez com o ID correto
        verify(vendaRepository, times(1)).deleteById(id);
    }
}
