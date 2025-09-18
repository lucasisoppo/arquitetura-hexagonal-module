package com.imobiliaria.application.port.out;

import com.imobiliaria.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarPorId(Long id);
    List<Cliente> buscarTodos();
    void deletar(Long id);
}
