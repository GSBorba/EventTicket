package com.lasoft.ticket.evento;

import com.lasoft.ticket.evento.dtos.DadosCadastroEvento;
import com.lasoft.ticket.evento.dtos.DadosEvento;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("evento")
public class EventoController {

    private final EventoRepository repository;

    @Autowired
    public EventoController(EventoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosEvento> cadastro(@RequestBody DadosCadastroEvento dados, UriComponentsBuilder uriBuilder) {
        Evento evento = new Evento(dados);
        repository.save(evento);

        URI uri = uriBuilder.path("evento/{id}").buildAndExpand(evento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosEvento(evento));
    }

    @GetMapping
    public ResponseEntity<Page<DadosEvento>> busca(@PageableDefault(size = 10) Pageable pageable) {
        Page<DadosEvento> eventos = repository.findAll(pageable).map(DadosEvento::new);
        return ResponseEntity.ok(eventos);
    }
}
