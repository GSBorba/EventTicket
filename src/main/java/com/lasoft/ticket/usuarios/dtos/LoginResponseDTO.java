package com.lasoft.ticket.usuarios.dtos;

public record LoginResponseDTO(String token) {

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}
