package com.ciceropinheiro.apivendas.apivendas.repository;

import com.ciceropinheiro.apivendas.apivendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
