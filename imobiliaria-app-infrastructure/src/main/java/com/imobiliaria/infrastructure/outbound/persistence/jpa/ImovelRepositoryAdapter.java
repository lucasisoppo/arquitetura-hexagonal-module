package com.imobiliaria.infrastructure.outbound.persistence.jpa;


import com.imobiliaria.domain.model.Imovel;
import com.imobiliaria.application.port.out.ImovelRepositoryPort;
import com.imobiliaria.infrastructure.outbound.persistence.jpa.mapper.ClienteMapper;
import com.imobiliaria.infrastructure.outbound.persistence.jpa.mapper.ImovelMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ImovelRepositoryAdapter implements ImovelRepositoryPort, PanacheRepositoryBase<ImovelEntity, Long> {

    @Inject
    ClienteMapper clienteMapper;

    @Inject
    ImovelMapper imovelMapper;

    @Inject
    ClienteRepositoryAdapter clienteRepositoryAdapter;

    @Override
    public Imovel salvar(Imovel imovel) {
        ImovelEntity imovelEntity = imovelMapper.toEntity(imovel);

        ClienteEntity clienteEntity = clienteMapper.toEntity(imovel.getCliente());
        imovelEntity.setCliente(clienteEntity);

        persist(imovelEntity);

        return imovelMapper.toDomain(imovelEntity);
    }
    @Override
    public List<Imovel> buscarImoveisPorCliente(Long clienteId) {
        Optional<ClienteEntity> clienteEntity = clienteRepositoryAdapter.findByIdOptional(clienteId);

        if (clienteEntity.isEmpty()) {
            return List.of();
        }

        List<ImovelEntity> imoveis = find("cliente", clienteEntity.get()).list();

        return imoveis.stream()
                .map(imovelMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Imovel> buscarTodos() {
        return findAll().stream()
                .map(imovelMapper::toDomain)
                .collect(Collectors.toList());
    }
}