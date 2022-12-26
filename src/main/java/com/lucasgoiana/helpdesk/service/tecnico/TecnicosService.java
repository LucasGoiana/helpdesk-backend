package com.lucasgoiana.helpdesk.service.tecnico;

import com.lucasgoiana.helpdesk.dto.TecnicoDTO;
import com.lucasgoiana.helpdesk.entities.Tecnico;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TecnicosService {

    Tecnico findById(Integer id);

  List<Tecnico> findAll();
}
