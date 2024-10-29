package com.lasoft.ticket.ingressos;

import com.lasoft.ticket.ingressos.dtos.DadosCadastroIngresso;
import com.lasoft.ticket.venda.Venda;
import com.lasoft.ticket.venda.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IngressoService {

    private final IngressoRepository ingressoRepository;
    private final VendaRepository vendaRepository;

    @Autowired
    public IngressoService(IngressoRepository ingressoRepository,
                           VendaRepository vendaRepository) {
        this.ingressoRepository = ingressoRepository;
        this.vendaRepository = vendaRepository;
    }

    @Transactional
    public Ingresso validaCadastro(DadosCadastroIngresso dados) {
        Venda venda = vendaRepository.getReferenceById(dados.venda());
        Ingresso ingresso = new Ingresso(dados.nome(), dados.cpf(), dados.telefone(), dados.email(), venda);

        ingressoRepository.save(ingresso);
        return ingresso;
    }

    @Transactional
    public Ingresso marcarEntrada(UUID id) {
        Ingresso ingresso = ingressoRepository.getReferenceById(id);
        ingresso.marcarEntrada();
        return ingresso;
    }
}
