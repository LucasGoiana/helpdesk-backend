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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class TecnicosServiceImpl implements TecnicosService {

    private final TecnicoRepository tecnicoRepository;

    private final PessoaRepository pessoaRepository;

    private final ChamadoRepository chamadoRepository;


   // private BCryptPasswordEncoder encoder;

    public TecnicosServiceImpl(TecnicoRepository tecnicoRepository, PessoaRepository pessoaRepository, ChamadoRepository chamadoRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.pessoaRepository = pessoaRepository;
        this.chamadoRepository = chamadoRepository;

        com.lucasgoiana.helpdesk.entities.Tecnico tec1 = new com.lucasgoiana.helpdesk.entities.Tecnico(null, "Valdir Cezar", "550.482.150-95", "valdir@mail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);
        com.lucasgoiana.helpdesk.entities.Tecnico tec2 = new com.lucasgoiana.helpdesk.entities.Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", "123");
        com.lucasgoiana.helpdesk.entities.Tecnico tec3 = new com.lucasgoiana.helpdesk.entities.Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", "123");
        com.lucasgoiana.helpdesk.entities.Tecnico tec4 = new com.lucasgoiana.helpdesk.entities.Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", "123");
        com.lucasgoiana.helpdesk.entities.Tecnico tec5 = new com.lucasgoiana.helpdesk.entities.Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", "123");

        Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", "123");
        Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", "123");
        Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", "123");
        Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", "123");
        Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

        pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

    }

    @Override
    public com.lucasgoiana.helpdesk.entities.Tecnico findById(Integer id) {
        var obj = tecnicoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tecnico Não Encontrado"));
        return obj;
    }

    @Override
    public List<com.lucasgoiana.helpdesk.entities.Tecnico> findAll() {
        List<com.lucasgoiana.helpdesk.entities.Tecnico> obj = tecnicoRepository.findAll();
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

    private void validaCPFEEmail(Integer id, String cpf, String email){
        var validaCpf = pessoaRepository.findByCpf(cpf);
        var validaEmail = pessoaRepository.findByEmail(email);

        if (validaCpf.isPresent() && validaCpf.get().getId() !=  id){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF Já Cadastrado!");
        }

        if (validaEmail.isPresent() && validaEmail.get().getId() !=  id){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail Já Cadastrado!");
        }


    }
}
