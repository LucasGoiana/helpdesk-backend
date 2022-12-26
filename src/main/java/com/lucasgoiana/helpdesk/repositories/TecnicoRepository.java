package com.lucasgoiana.helpdesk.repositories;

import com.lucasgoiana.helpdesk.entities.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
