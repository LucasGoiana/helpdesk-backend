package com.lucasgoiana.helpdesk.service.cliente;

import com.lucasgoiana.helpdesk.dto.ClienteDTO;
import com.lucasgoiana.helpdesk.entities.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente findById(Integer id);

    List<Cliente> findAll();

    Cliente create(ClienteDTO clienteDTO);

    Cliente update(Integer id, ClienteDTO clienteDTO);

    void delete(Integer id);
}
