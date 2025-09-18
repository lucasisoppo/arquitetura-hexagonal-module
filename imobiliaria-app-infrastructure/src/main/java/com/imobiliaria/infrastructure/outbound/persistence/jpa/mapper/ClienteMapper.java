package com.imobiliaria.infrastructure.outbound.persistence.jpa.mapper;

import com.imobiliaria.domain.model.Cliente;
import com.imobiliaria.infrastructure.outbound.persistence.jpa.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ClienteMapper {
    Cliente toDomain(ClienteEntity entity);
    ClienteEntity toEntity(Cliente domain);
}
