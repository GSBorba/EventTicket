package com.lasoft.ticket.venda;

import com.lasoft.ticket.evento.Evento;
import com.lasoft.ticket.evento.EventoRepository;
import com.lasoft.ticket.venda.dtos.DadosCadastroVenda;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final EventoRepository eventoRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository,
                        EventoRepository eventoRepository) {
        this.vendaRepository = vendaRepository;
        this.eventoRepository = eventoRepository;
    }

    @Transactional
    public Venda validaCadastro(DadosCadastroVenda dados) {
        Evento evento = eventoRepository.getReferenceById(dados.evento());
        Venda venda = new Venda(dados.vendedor(), dados.data(), dados.quantidade(), evento);

        vendaRepository.save(venda);
        return venda;
    }
}
