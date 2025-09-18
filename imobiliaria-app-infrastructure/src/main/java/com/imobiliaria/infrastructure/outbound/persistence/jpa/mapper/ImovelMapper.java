package com.imobiliaria.infrastructure.outbound.persistence.jpa.mapper;

import com.imobiliaria.domain.model.Imovel;
import com.imobiliaria.infrastructure.outbound.persistence.jpa.ImovelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ImovelMapper {
    Imovel toDomain(ImovelEntity entity);
    ImovelEntity toEntity(Imovel domain);
}
