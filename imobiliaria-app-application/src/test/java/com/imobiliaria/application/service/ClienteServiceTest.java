package com.imobiliaria.application.service;

import com.imobiliaria.application.validation.ClienteValidator;
import com.imobiliaria.domain.model.Cliente;
import com.imobiliaria.application.port.out.ClienteRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    ClienteRepositoryPort clienteRepositoryPort;

    @Mock
    ClienteValidator clienteValidator;

    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        clienteService = new ClienteService(clienteRepositoryPort, clienteValidator);
    }

    @Test
    void testAtualizarClienteComSucesso() {
        Long clienteId = 1L;
        Cliente clienteOriginal = new Cliente(clienteId, "Joao", "123.456.789-00", LocalDate.of(1990, 1, 1), true);
        Cliente clienteComNovosDados = new Cliente(clienteId, "Joao Silva", "123.456.789-00", LocalDate.of(1990, 1, 1), true);

        when(clienteRepositoryPort.buscarPorId(clienteId)).thenReturn(Optional.of(clienteOriginal));
        when(clienteRepositoryPort.salvar(Mockito.any(Cliente.class))).thenReturn(clienteComNovosDados);

        Cliente resultado = clienteService.atualizarCliente(clienteComNovosDados);

        assertNotNull(resultado);
        assertEquals("Joao Silva", resultado.getNome());

        verify(clienteRepositoryPort, Mockito.times(1)).buscarPorId(clienteId);
        verify(clienteValidator, Mockito.times(1)).validate(Mockito.any(Cliente.class), Mockito.any(Cliente.class));
        verify(clienteRepositoryPort, Mockito.times(1)).salvar(Mockito.any(Cliente.class));
    }

    @Test
    void testAtualizarClienteInexistente() {
        Long clienteId = 99L;
        Cliente clienteInexistente = new Cliente(clienteId, "Inexistente", "000", LocalDate.now(), true);
        when(clienteRepositoryPort.buscarPorId(clienteId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> clienteService.atualizarCliente(clienteInexistente));
    }
}