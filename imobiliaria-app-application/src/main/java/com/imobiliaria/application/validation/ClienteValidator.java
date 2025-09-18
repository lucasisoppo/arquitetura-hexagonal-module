package com.imobiliaria.application.validation;

import com.imobiliaria.domain.model.Cliente;
import com.imobiliaria.domain.model.Imovel;
import com.imobiliaria.application.port.out.ImovelRepositoryPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ClienteValidator {

    private final ImovelRepositoryPort imovelRepositoryPort;

    @Inject
    public ClienteValidator(ImovelRepositoryPort imovelRepositoryPort) {
        this.imovelRepositoryPort = imovelRepositoryPort;
    }

    public void validate(Cliente original, Cliente updated) {
        this.validate(updated);
        validateExistClient(original);
        validateInactiveClientUpdate(original, updated);
    }

    public void validate(Cliente updated) {
        validateClienteComImoveis(updated);
    }

    private void validateClienteComImoveis(Cliente cliente) {
        // Validação: um cliente não pode ser criado se já possui imóveis cadastrados
        if (cliente.getId() != null) {
            List<Imovel> imoveisDoCliente = imovelRepositoryPort.buscarImoveisPorCliente(cliente.getId());
            if (!imoveisDoCliente.isEmpty()) {
                throw new IllegalStateException("Cliente já possui imóveis cadastrados e não pode ser criado novamente.");
            }
        }
    }

    private void validateExistClient(Cliente original) {
        if (Objects.isNull(original)) {
            throw new IllegalStateException("Cliente não encontrado!");
        }
    }

    private void validateInactiveClientUpdate(Cliente original, Cliente updated) {
        if (!original.isAtivo()) {
            boolean isOnlyActiveFieldChanged = updated.isAtivo() != original.isAtivo();

            if (isOnlyActiveFieldChanged) {
                if (!original.getNome().equals(updated.getNome()) ||
                        !original.getCpf().equals(updated.getCpf()) ||
                        !original.getDataNascimento().equals(updated.getDataNascimento())) {
                    throw new IllegalStateException("Não é possível alterar outros campos de um cliente inativo.");
                }
            } else {
                throw new IllegalStateException("Cliente inativo não permite outras alterações.");
            }
        }
    }
}
