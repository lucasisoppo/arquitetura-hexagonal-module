package com.imobiliaria.infrastructure.outbound.api.cep;

import com.imobiliaria.infrastructure.dto.CepInfoDto;
import com.imobiliaria.application.port.out.CepServicePort;
import com.imobiliaria.domain.model.CepInfo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class ViaCepAdapter implements CepServicePort {

    @Inject
    @RestClient
    ViaCepClient viaCepClient;

    @Override
    public Optional<CepInfo> buscarCep(String cep) {
        try {
            Response response = viaCepClient.buscarCep(cep);

            CepInfoDto dto = response.readEntity(CepInfoDto.class);

            return Optional.of(dto.toDomain());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}