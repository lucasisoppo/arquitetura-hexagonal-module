package com.imobiliaria.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CepInfo {
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
