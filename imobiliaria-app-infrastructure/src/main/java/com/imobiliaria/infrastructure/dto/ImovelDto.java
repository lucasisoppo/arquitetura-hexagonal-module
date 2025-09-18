package com.imobiliaria.infrastructure.dto;


import com.imobiliaria.domain.model.Imovel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImovelDto {

    public Long id;
    public String endereco;
    public String bairro;
    public String cidade;
    public String uf;
    public String cep;
    public BigDecimal valor;
    public ClienteDto cliente;


    // Método de conversão para o modelo de domínio
    public Imovel toDomain() {
        return Imovel.builder()
                .id(this.id)
                .endereco(this.endereco)
                .bairro(this.bairro)
                .cidade(this.cidade)
                .uf(this.uf)
                .cep(this.cep)
                .valor(this.valor)
                .cliente(this.cliente.toDomain())
                .build();
    }

    // Método de conversão do modelo de domínio para o DTO
    public static ImovelDto fromDomain(Imovel imovel) {
        if (imovel == null) return null;
        ImovelDto dto = new ImovelDto();
        dto.setId(imovel.getId());
        dto.setEndereco(imovel.getEndereco());
        dto.setBairro(imovel.getBairro());
        dto.setCidade(imovel.getCidade());
        dto.setCep(imovel.getCep());
        dto.setValor(imovel.getValor());
        dto.setCliente(ClienteDto.fromDomain(imovel.getCliente()));
        return dto;
    }
}