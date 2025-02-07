package com.lasoft.ticket.usuarios;

import com.lasoft.ticket.enums.Funcoes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuarios implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuarios")
    private String id;
    @Column(name = "nm_login")
    private String login;
    @Column(name = "ds_senha")
    private String senha;
    @Column(name = "ds_funcao")
    @Enumerated(EnumType.STRING)
    private Funcoes funcao;

    public Usuarios(String login, String senha, Funcoes funcao) {
        this.login = login;
        this.senha = senha;
        this.funcao = funcao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.funcao == Funcoes.ADM) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),  new SimpleGrantedAuthority("ROLE_RECEPCIONISTA"), new SimpleGrantedAuthority("ROLE_USER"));
        } else if(this.funcao == Funcoes.REC) {
            return List.of(new SimpleGrantedAuthority("ROLE_RECEPCIONISTA"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
