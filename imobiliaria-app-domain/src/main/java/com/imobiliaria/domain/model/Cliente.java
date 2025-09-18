package com.imobiliaria.domain.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cliente {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private boolean ativo = true;

    public static class ClienteBuilder {
        public Cliente build() {
            // Chamando o método de validação antes de construir o objeto
            //validações intrisecas ficam dentro do dominio/modelo,
            // validações de sistema que dependam de postas de saidas devem ficar na application
//            validarCamposObrigatorios();
//            validarIdade();
            return new Cliente(id, nome, cpf, dataNascimento, ativo);
        }

        private void validarCamposObrigatorios() {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("O nome do cliente é obrigatório.");
            }
            if (cpf == null || cpf.trim().isEmpty()) {
                throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
            }
            if (dataNascimento == null) {
                throw new IllegalArgumentException("A data de nascimento do cliente é obrigatória.");
            }
        }

        private void validarIdade() {
            if (LocalDate.now().getYear() - this.dataNascimento.getYear() < 18) {
                throw new IllegalArgumentException("O cliente deve ser maior de idade.");
            }
        }
    }
}
