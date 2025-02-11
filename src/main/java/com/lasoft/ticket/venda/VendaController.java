package com.lasoft.ticket.venda;

import com.lasoft.ticket.venda.dtos.DadosCadastroVenda;
import com.lasoft.ticket.venda.dtos.DadosVenda;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = {"http://localhost:3000", "https://test-frontend-ticket-event.netlify.app"})
@RestController
@RequestMapping("venda")
public class VendaController {

    private final VendaRepository repository;
    private final VendaService service;

    @Autowired
    public VendaController(VendaRepository repository,
                           VendaService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosVenda> cadastro(@RequestBody DadosCadastroVenda dados, UriComponentsBuilder uriBuilder) {
        Venda venda = service.validaCadastro(dados);

        URI uri = uriBuilder.path("venda/{id}").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosVenda(venda));
    }

    @GetMapping
    public ResponseEntity<Page<DadosVenda>> busca(@PageableDefault(size = 10) Pageable pageable) {
        Page<DadosVenda> venda = repository.findAll(pageable).map(DadosVenda::new);
        return ResponseEntity.ok(venda);
    }
}
