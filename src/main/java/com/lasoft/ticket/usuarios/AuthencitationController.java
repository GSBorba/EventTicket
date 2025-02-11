package com.lasoft.ticket.usuarios;

import com.lasoft.ticket.infra.security.TokenService;
import com.lasoft.ticket.usuarios.dtos.AuthenticationDTO;
import com.lasoft.ticket.usuarios.dtos.LoginResponseDTO;
import com.lasoft.ticket.usuarios.dtos.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "https://test-frontend-ticket-event.netlify.app"})
@RestController
@RequestMapping("auth")
public class AuthencitationController {

    private final AuthenticationManager authenticationManager;
    private final UsuariosRepository usuariosRepository;
    private final TokenService tokenService;

    @Autowired
    public AuthencitationController(AuthenticationManager authenticationManager,
                                    UsuariosRepository usuariosRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuariosRepository = usuariosRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuarios) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.usuariosRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuarios usuario = new Usuarios(data.login(), encryptedPassword, data.role());

        this.usuariosRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
