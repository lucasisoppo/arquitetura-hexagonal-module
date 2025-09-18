package com.imobiliaria.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imobiliaria.domain.model.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ClienteDto {

    private Long id;
    private String nome;
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private boolean ativo = true;

    // Método de conversão para o modelo de domínio
    public Cliente toDomain() {
        return Cliente.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .dataNascimento(this.dataNascimento)
                .ativo(this.ativo)
                .build();
    }

    // Método de conversão do modelo de domínio para o DTO
    public static ClienteDto fromDomain(Cliente cliente) {
        if (cliente == null) return null;
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCpf());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setAtivo(cliente.isAtivo());
        return dto;
    }
}
