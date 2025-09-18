package com.imobiliaria.infrastructure.dto;

import com.imobiliaria.domain.model.CepInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CepInfoDto {
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;


    // Método de conversão para o modelo de domínio
    public CepInfo toDomain() {
        return CepInfo.builder()
                .uf(this.uf)
                .bairro(this.bairro)
                .localidade(this.localidade)
                .logradouro(this.logradouro)
                .build();
    }

    // Método de conversão do modelo de domínio para o DTO
    public static CepInfoDto fromDomain(CepInfo cepInfo) {
        if (cepInfo == null) return null;
        CepInfoDto dto = new CepInfoDto();
        dto.setLogradouro(cepInfo.getLogradouro());
        dto.setBairro(cepInfo.getBairro());
        dto.setLocalidade(cepInfo.getLocalidade());
        dto.setUf(cepInfo.getUf());
        return dto;
    }
}
