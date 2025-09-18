package com.imobiliaria.application.port.out;

import com.imobiliaria.domain.model.CepInfo;

import java.util.Optional;

public interface CepServicePort {
    Optional<CepInfo> buscarCep(String cep);
}