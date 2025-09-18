package com.imobiliaria.infrastructure.outbound.persistence.jpa;

import com.imobiliaria.domain.model.Cliente;
import com.imobiliaria.application.port.out.ClienteRepositoryPort;
import com.imobiliaria.infrastructure.outbound.persistence.jpa.mapper.ClienteMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClienteRepositoryAdapter implements ClienteRepositoryPort, PanacheRepositoryBase<ClienteEntity, Long> {

    @Inject
    ClienteMapper clienteMapper;
    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity entity = clienteMapper.toEntity(cliente);
        persist(entity);
        return clienteMapper.toDomain(entity);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return findByIdOptional(id).map(clienteMapper::toDomain);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return findAll().stream()
                .map(clienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(Long id) {
        deleteById(id);
    }
}
