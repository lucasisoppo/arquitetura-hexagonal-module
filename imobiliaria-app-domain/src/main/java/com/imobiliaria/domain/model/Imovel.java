package com.imobiliaria.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Imovel {

    public Long id;
    public String endereco;
    public String bairro;
    public String cidade;
    public String uf;
    public String cep;
    public BigDecimal valor;
    public Cliente cliente;
}