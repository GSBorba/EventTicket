package com.lasoft.ticket.venda.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosCadastroVenda(
    @NotBlank
    String vendedor,
    @NotNull
    LocalDateTime data,
    @NotNull
    Integer quantidade,
    @NotNull
    @org.hibernate.validator.constraints.UUID
    UUID evento
    ) {
}
