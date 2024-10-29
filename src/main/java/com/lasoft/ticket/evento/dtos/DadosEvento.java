package com.lasoft.ticket.evento.dtos;

import com.lasoft.ticket.evento.Evento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record DadosEvento(UUID id, String nome, LocalDate data, LocalTime hora, String local) {

    public DadosEvento(Evento evento) {
        this(evento.getId(), evento.getNome(), evento.getData(), evento.getHora(), evento.getLocal());
    }
}
