package com.imobiliaria.application.validation;

import com.imobiliaria.domain.model.Cliente;
import com.imobiliaria.domain.model.Imovel;
import com.imobiliaria.application.port.out.ImovelRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteValidatorTest {

    @Mock
    ImovelRepositoryPort imovelRepositoryPort;

    private ClienteValidator clienteValidator;

    @BeforeEach
    void setUp() {
        clienteValidator = new ClienteValidator(imovelRepositoryPort);
    }

    @Test
    void testValidateOnCreateComImoveisCadastrados() {
        Long clienteId = 1L;
        Cliente cliente = new Cliente(clienteId, "Ana", "123", LocalDate.now(), true);
        when(imovelRepositoryPort.buscarImoveisPorCliente(clienteId)).thenReturn(Collections.singletonList(new Imovel())); // Retorna uma lista não vazia

        assertThrows(IllegalStateException.class, () -> clienteValidator.validate(cliente));
    }

    @Test
    void testValidateOnUpdateClienteInativoComOutrasAlteracoes() {
        Cliente clienteOriginal = new Cliente(1L, "Pedro", "111", LocalDate.now(), false);
        Cliente clienteAtualizado = new Cliente(1L, "Pedro", "222", LocalDate.now(), false); // CPF alterado

        assertThrows(IllegalStateException.class, () -> clienteValidator.validate(clienteOriginal, clienteAtualizado));
    }

    @Test
    void testValidateOnUpdateClienteInativoAlterandoApenasAtivo() {
        Cliente clienteOriginal = new Cliente(1L, "Pedro", "111", LocalDate.now(), false);
        Cliente clienteAtualizado = new Cliente(1L, "Pedro", "111", LocalDate.now(), true); // Apenas 'ativo' alterado

        assertDoesNotThrow(() -> clienteValidator.validate(clienteOriginal, clienteAtualizado));
    }
}