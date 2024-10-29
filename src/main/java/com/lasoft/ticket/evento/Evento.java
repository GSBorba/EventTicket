package com.lasoft.ticket.evento;

import com.lasoft.ticket.evento.dtos.DadosCadastroEvento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table(name = "evento")
@Entity(name = "Evento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_evento")
    private UUID id;
    @Column(name = "nm_evento")
    private String nome;
    @Column(name = "dt_evento")
    private LocalDate data;
    @Column(name = "hr_evento")
    private LocalTime hora;
    @Column(name = "ds_local")
    private String local;

    public Evento(DadosCadastroEvento dados) {
        this.nome = dados.nome();
        this.data = dados.data();
        this.hora = dados.hora();
        this.local = dados.local();
    }
}
