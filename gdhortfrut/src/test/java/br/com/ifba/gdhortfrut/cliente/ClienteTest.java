package br.com.ifba.gdhortfrut.cliente;

import br.com.ifba.gdhortfrut.cliente.entity.Cliente;
import br.com.ifba.gdhortfrut.cliente.repository.ClienteRepository;
import br.com.ifba.gdhortfrut.cliente.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    private ClienteRepository clienteRepository;
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        clienteRepository = Mockito.mock(ClienteRepository.class);
        clienteService = new ClienteService(clienteRepository);
    }

    @Test
    void testSave() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("12345678900");
        cliente.setTelefone("99999-9999");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente saved = clienteService.save(cliente);

        assertEquals("João", saved.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        Cliente existente = new Cliente();
        existente.setNome("Maria");
        existente.setCpf("11111111111");
        existente.setTelefone("88888-8888");

        Cliente atualizado = new Cliente();
        atualizado.setNome("Ana");
        atualizado.setCpf("22222222222");
        atualizado.setTelefone("77777-7777");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(existente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(atualizado);

        Cliente result = clienteService.update(atualizado, id);

        assertEquals("Ana", result.getNome());
        verify(clienteRepository).findById(id);
        verify(clienteRepository).save(existente);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(clienteRepository).deleteById(id);

        clienteService.delete(id);

        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setNome("Carlos");
        cliente.setCpf("33333333333");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Optional<Cliente> found = clienteService.findById(id);

        assertTrue(found.isPresent());
        assertEquals("Carlos", found.get().getNome());
    }

    @Test
    void testFindAll() {
        Cliente c1 = new Cliente();
        c1.setNome("João");

        Cliente c2 = new Cliente();
        c2.setNome("Maria");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Cliente> clientes = clienteService.findAll();

        assertEquals(2, clientes.size());
        assertEquals("João", clientes.get(0).getNome());
    }
}
