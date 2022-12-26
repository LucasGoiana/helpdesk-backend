package com.lucasgoiana.helpdesk.repositories;

import com.lucasgoiana.helpdesk.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
