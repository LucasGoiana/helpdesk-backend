package com.lucasgoiana.helpdesk.service.chamados;

import com.lucasgoiana.helpdesk.dto.ChamadoDTO;
import com.lucasgoiana.helpdesk.entities.Chamado;
import com.lucasgoiana.helpdesk.entities.Cliente;
import com.lucasgoiana.helpdesk.entities.Tecnico;
import com.lucasgoiana.helpdesk.enums.Prioridade;
import com.lucasgoiana.helpdesk.enums.Status;
import com.lucasgoiana.helpdesk.repositories.ChamadoRepository;
import com.lucasgoiana.helpdesk.service.cliente.ClienteService;
import com.lucasgoiana.helpdesk.service.tecnico.TecnicosService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ChamadoServiceImpl implements ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final TecnicosService tecnicosService;
    private final ClienteService clienteService;
    public ChamadoServiceImpl(ChamadoRepository chamadoRepository, TecnicosService tecnicosService, ClienteService clienteService) {
        this.chamadoRepository = chamadoRepository;
        this.tecnicosService = tecnicosService;
        this.clienteService = clienteService;
    }

    @Override
    public Chamado findById(Integer id) {
        var obj = chamadoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado Não Encontrado"));
        return obj;
    }

    @Override
    public List<Chamado> findAll() {
        List<Chamado> obj = chamadoRepository.findAll();
        return obj;
    }

    @Override
    public Chamado create(ChamadoDTO chamadoDTO) {
        return chamadoRepository.save(newChamado(chamadoDTO));
    }

    private Chamado newChamado(ChamadoDTO chamadoDTO){
        Tecnico tecnico = tecnicosService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

        Chamado chamado = new Chamado(cliente, tecnico, chamadoDTO);
        if (chamadoDTO.getId() != null){
            chamado.setId(chamadoDTO.getId());
        }

        return chamado;
    }



    @Override
    public Chamado update(Integer id, ChamadoDTO chamadoDTO) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
