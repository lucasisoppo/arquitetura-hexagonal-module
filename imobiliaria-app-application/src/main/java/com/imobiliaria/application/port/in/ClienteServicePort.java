package com.imobiliaria.application.port.in;

import com.imobiliaria.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServicePort {
    Cliente criarCliente(Cliente cliente);
    Optional<Cliente> buscarClientePorId(Long id);
    List<Cliente> listarTodosClientes();
/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Deleta um cliente com base no seu ID.
     *
     * @param id o ID do cliente a ser deletado
     */
/* <<<<<<<<<<  446efce3-87b0-4d99-9ef2-26192e855197  >>>>>>>>>>> */
    void deletarCliente(Long id);
    Cliente atualizarCliente(Cliente cliente);
}
