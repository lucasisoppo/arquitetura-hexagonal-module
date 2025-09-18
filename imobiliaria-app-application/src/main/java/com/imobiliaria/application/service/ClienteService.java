package com.imobiliaria.application.service;

import com.imobiliaria.application.validation.ClienteValidator;
import com.imobiliaria.domain.model.Cliente;
import com.imobiliaria.application.port.in.ClienteServicePort;
import com.imobiliaria.application.port.out.ClienteRepositoryPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteService implements ClienteServicePort {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final ClienteValidator clienteValidator;

    @Inject
    public ClienteService(ClienteRepositoryPort clienteRepositoryPort, ClienteValidator clienteValidator) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.clienteValidator = clienteValidator;
    }

    @Override
    @Transactional
    public Cliente criarCliente(Cliente cliente) {
        // Exemplo de lógica de negócio: validação, enriquecimento de dados, etc.
        // Aqui o serviço poderia chamar outras portas de saída, como um serviço de notificação.
        // Exemplo de validação: Verifica se o cliente já tem imóveis
        // validações intrisecas ficam dentro do dominio/modelo,
        // validações de sistema que dependam de portas de saidas ficam na application

        clienteValidator.validate(cliente);
        return clienteRepositoryPort.salvar(cliente);
    }

    @Override
    @Transactional
    public Cliente atualizarCliente(Cliente cliente) {
        // As validações triviais de builder já foram feitas ao criar o objeto 'cliente'
        // Validação da regra de negócio: não pode atualizar cliente inativo
        Optional<Cliente> clienteExistente = clienteRepositoryPort.buscarPorId(cliente.getId());
        clienteValidator.validate(clienteExistente.get(), cliente);
        return clienteRepositoryPort.salvar(cliente);
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepositoryPort.buscarPorId(id);
    }

    @Override
    public List<Cliente> listarTodosClientes() {
        return clienteRepositoryPort.buscarTodos();
    }

    @Override
    @Transactional
    public void deletarCliente(Long id) {
        clienteRepositoryPort.deletar(id);
    }
}