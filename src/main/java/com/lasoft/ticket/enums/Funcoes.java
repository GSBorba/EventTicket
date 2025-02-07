package com.lasoft.ticket.enums;

public enum Funcoes {
    ADM("Administrador"),
    REC("Recepcionista");

    private final String descricao;

    Funcoes(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
