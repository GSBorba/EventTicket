package com.lasoft.ticket.ingressos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngressoRepository extends JpaRepository<Ingresso, UUID> {
}
