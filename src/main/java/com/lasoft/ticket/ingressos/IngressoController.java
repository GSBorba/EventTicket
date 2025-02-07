package com.lasoft.ticket.ingressos;

import com.lasoft.ticket.ingressos.dtos.DadosCadastroIngresso;
import com.lasoft.ticket.ingressos.dtos.DadosIngresso;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin(origins = "*")
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
    public ResponseEntity<byte[]> cadastro(@RequestBody DadosCadastroIngresso dados, HttpServletRequest request) throws IOException, WriterException {
        Ingresso ingresso = service.validaCadastro(dados);

        // Obter a URL da aplicação web que fez a requisição
        String refererUrl = request.getHeader("Referer");

        // Se o Referer não estiver presente, use um valor padrão ou lance uma exceção
        if (refererUrl == null) {
            refererUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"; // Fallback ou trate como erro
        }

        // Construir a URL completa com o caminho /ingresso/{id}
        String urlCompleta = refererUrl + "ingresso/" + ingresso.getId();

        // Gerar o QR code com a URL completa
        byte[] qrCodeImage = generateQRCode(urlCompleta);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(qrCodeImage, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<DadosIngresso>> busca(@PageableDefault(size = 10) Pageable pageable) {
        Page<DadosIngresso> ingresso = repository.findAll(pageable).map(DadosIngresso::new);
        return ResponseEntity.ok(ingresso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosIngresso> buscaPeloID(@PathVariable UUID id) {
        Ingresso ingresso = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosIngresso(ingresso));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosIngresso> marcarEntrada(@PathVariable UUID id) {
        Ingresso ingresso = service.marcarEntrada(id);

        return ResponseEntity.ok(new DadosIngresso(ingresso));
    }

    private byte[] generateQRCode(String text) throws WriterException, IOException {
        int width = 300;
        int height = 300;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }
}
