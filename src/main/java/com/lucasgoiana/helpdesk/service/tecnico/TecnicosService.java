package com.lucasgoiana.helpdesk.service.tecnico;

import com.lucasgoiana.helpdesk.entities.Tecnico;
import org.springframework.http.ResponseEntity;

public interface TecnicosService {

    Tecnico findById(Integer id);
}
