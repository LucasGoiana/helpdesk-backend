package com.lucasgoiana.helpdesk.repositories;

import com.lucasgoiana.helpdesk.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
