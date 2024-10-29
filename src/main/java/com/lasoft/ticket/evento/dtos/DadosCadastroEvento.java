package com.lasoft.ticket.evento.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DadosCadastroEvento(
    @NotBlank
    String nome,
    @NotNull
    LocalDate data,
    @NotNull
    LocalTime hora,
    @NotBlank
    String local
    ) {
}
