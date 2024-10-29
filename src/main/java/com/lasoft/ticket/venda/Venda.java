package com.lasoft.ticket.venda;

import com.lasoft.ticket.evento.Evento;
import com.lasoft.ticket.venda.dtos.DadosCadastroVenda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "venda")
@Entity(name = "Venda")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_venda")
    private UUID id;
    @Column(name = "nm_vendedor")
    private String vendedor;
    @Column(name = "dt_venda")
    private LocalDateTime data;
    @Column(name = "qt_venda")
    private Integer quantidade;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_evento")
    private Evento evento;

    public Venda(String vendedor, LocalDateTime data, Integer quantidade, Evento evento) {
        this.vendedor = vendedor;
        this.data = data;
        this.quantidade = quantidade;
        this.evento = evento;
    }
}
