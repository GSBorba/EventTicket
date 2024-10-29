package com.lasoft.ticket.ingressos.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record DadosCadastroIngresso(
    @NotBlank
    String nome,
    @NotBlank
    @Pattern(regexp = "\\d{14}")
    String cpf,
    @NotBlank
    @Pattern(regexp = "\\d{15}")
    String telefone,
    @Email
    String email,
    @NotNull
    @org.hibernate.validator.constraints.UUID
    UUID venda
    ) {
}
