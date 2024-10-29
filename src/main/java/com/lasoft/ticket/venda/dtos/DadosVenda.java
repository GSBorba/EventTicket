package com.lasoft.ticket.venda.dtos;

import com.lasoft.ticket.venda.Venda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record DadosVenda(UUID id, String vendedor, LocalDateTime dataVenda, Integer quantidade, String nomeEvento, LocalDate dataEvento, LocalTime horaEvento, String local) {

    public DadosVenda(Venda venda) {
        this(venda.getId(), venda.getVendedor(), venda.getData(), venda.getQuantidade(), venda.getEvento().getNome(), venda.getEvento().getData(), venda.getEvento().getHora(), venda.getEvento().getLocal());
    }
}
