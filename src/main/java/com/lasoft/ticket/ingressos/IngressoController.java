package com.lasoft.ticket.ingressos;

import com.lasoft.ticket.ingressos.dtos.DadosCadastroIngresso;
import com.lasoft.ticket.ingressos.dtos.DadosIngresso;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("ingresso")
public class IngressoController {

    private final IngressoRepository repository;
    private final IngressoService service;

    @Autowired
    public IngressoController(IngressoRepository repository,
                              IngressoService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosIngresso> cadastro(@RequestBody DadosCadastroIngresso dados, UriComponentsBuilder uriBuilder) throws IOException, WriterException {
        Ingresso ingresso = service.validaCadastro(dados);

        URI uri = uriBuilder.path("Ingresso/{id}").buildAndExpand(ingresso.getId()).toUri();

        generateQRCode(uri.toString(), "QRCode_" + ingresso.getId() + ".png");

        return ResponseEntity.created(uri).body(new DadosIngresso(ingresso));
    }

    @GetMapping
    public ResponseEntity<Page<DadosIngresso>> busca(@PageableDefault(size = 10) Pageable pageable) {
        Page<DadosIngresso> ingresso = repository.findAll(pageable).map(DadosIngresso::new);
        return ResponseEntity.ok(ingresso);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosIngresso> marcarEntrada(@PathVariable UUID id) {
        Ingresso ingresso = service.marcarEntrada(id);

        return ResponseEntity.ok(new DadosIngresso(ingresso));
    }

    private void generateQRCode(String text, String filePath) throws WriterException, IOException {
        int width = 300;
        int height = 300;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
