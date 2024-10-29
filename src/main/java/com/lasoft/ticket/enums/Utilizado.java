package com.lasoft.ticket.enums;

public enum Utilizado {
    S("Sim"),
    N("Não");

    private final String descricao;

    Utilizado(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
