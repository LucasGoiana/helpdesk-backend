package com.lucasgoiana.helpdesk.service.chamados;

import com.lucasgoiana.helpdesk.dto.ChamadoDTO;
import com.lucasgoiana.helpdesk.dto.ClienteDTO;
import com.lucasgoiana.helpdesk.entities.Chamado;
import com.lucasgoiana.helpdesk.entities.Cliente;

import java.util.List;

public interface ChamadoService {

    Chamado findById(Integer id);

    List<Chamado> findAll();

    Chamado create(ChamadoDTO chamadoDTO);

    Chamado update(Integer id, ChamadoDTO chamadoDTO);
}
