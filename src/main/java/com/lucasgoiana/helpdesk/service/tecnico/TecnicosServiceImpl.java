package com.lucasgoiana.helpdesk.service.tecnico;

import com.lucasgoiana.helpdesk.dto.TecnicoDTO;
import com.lucasgoiana.helpdesk.entities.Chamado;
import com.lucasgoiana.helpdesk.entities.Cliente;
import com.lucasgoiana.helpdesk.entities.Tecnico;
import com.lucasgoiana.helpdesk.enums.Perfil;
import com.lucasgoiana.helpdesk.enums.Prioridade;
import com.lucasgoiana.helpdesk.enums.Status;
import com.lucasgoiana.helpdesk.repositories.ChamadoRepository;
import com.lucasgoiana.helpdesk.repositories.PessoaRepository;
import com.lucasgoiana.helpdesk.repositories.TecnicoRepository;
import com.lucasgoiana.helpdesk.service.PessoaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class TecnicosServiceImpl extends PessoaService implements TecnicosService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicosServiceImpl(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public Tecnico findById(Integer id) {
        var obj = tecnicoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tecnico Não Encontrado"));
        return obj;
    }

    @Override
    public List<Tecnico> findAll() {
        List<Tecnico> obj = tecnicoRepository.findAll();
        return obj;
    }

    @Override
    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        validaCPFEEmail(tecnicoDTO.getId(), tecnicoDTO.getCpf(), tecnicoDTO.getEmail());
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);
    }

    @Override
    public Tecnico update(Integer id, TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico oldObj = findById(id);
        validaCPFEEmail(tecnicoDTO.getId(), tecnicoDTO.getCpf(), tecnicoDTO.getEmail());
        oldObj = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(oldObj);
    }

    @Override
    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Existe Chamados associado a esse Técnico");
        }

        tecnicoRepository.deleteById(id);

    }

}
