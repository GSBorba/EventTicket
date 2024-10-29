package com.lasoft.ticket.ingressos;

import com.lasoft.ticket.enums.Utilizado;
import com.lasoft.ticket.venda.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "ingresso")
@Entity(name = "Ingresso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_ingresso")
    private UUID id;
    @Column(name = "nm_convidado")
    private String nome;
    @Column(name = "nr_cpf")
    private String cpf;
    @Column(name = "nr_telefone")
    private String telefone;
    @Column(name = "ds_email")
    private String email;
    @Column(name = "ie_utilizado")
    @Enumerated(EnumType.STRING)
    private Utilizado utilizado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public Ingresso(String nome, String cpf, String telefone, String email, Venda venda) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.utilizado = Utilizado.N;
        this.venda = venda;
    }

    public void marcarEntrada() {
        this.utilizado = Utilizado.S;
    }
}
