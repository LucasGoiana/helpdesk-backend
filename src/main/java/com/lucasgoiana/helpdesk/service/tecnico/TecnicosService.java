package com.lucasgoiana.helpdesk.service.tecnico;

import com.lucasgoiana.helpdesk.dto.TecnicoDTO;
import com.lucasgoiana.helpdesk.entities.Tecnico;

import java.util.List;

public interface TecnicosService {

    Tecnico findById(Integer id);

    List<Tecnico> findAll();

   Tecnico create(TecnicoDTO tecnicoDTO);

    Tecnico update(Integer id, TecnicoDTO tecnicoDTO);

    void delete(Integer id);
}
