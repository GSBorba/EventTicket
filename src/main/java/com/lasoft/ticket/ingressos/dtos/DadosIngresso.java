package com.lasoft.ticket.ingressos.dtos;

import com.lasoft.ticket.ingressos.Ingresso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record DadosIngresso(UUID id, String nomeConvidado, String cpf, String telefone, String email, String utilizado, String nomeVendedor, LocalDateTime dataVenda, String nomeEvento, LocalDate dataEvento, LocalTime horaEvento, String local) {

    public DadosIngresso(Ingresso ingresso) {
        this(ingresso.getId(), ingresso.getNome(), ingresso.getCpf(), ingresso.getTelefone(), ingresso.getEmail(), ingresso.getUtilizado().getDescricao(), ingresso.getVenda().getVendedor(), ingresso.getVenda().getData(), ingresso.getVenda().getEvento().getNome(), ingresso.getVenda().getEvento().getData(), ingresso.getVenda().getEvento().getHora(), ingresso.getVenda().getEvento().getLocal());
    }
}
