package com.imobiliaria.infrastructure.outbound.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity(name = "imovel")
@Getter
@Setter
@NoArgsConstructor
public class ImovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String endereco;
    private String cidade;
    private String uf;
    private BigDecimal valor;

    // Mapeamento para o cliente (opcional)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
}