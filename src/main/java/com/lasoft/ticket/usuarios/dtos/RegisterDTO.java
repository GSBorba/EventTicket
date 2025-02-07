package com.lasoft.ticket.usuarios.dtos;

import com.lasoft.ticket.enums.Funcoes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotNull
        Funcoes role) {
}
