package com.lucasgoiana.helpdesk.service;

import com.lucasgoiana.helpdesk.entities.Chamado;
import com.lucasgoiana.helpdesk.entities.Cliente;
import com.lucasgoiana.helpdesk.entities.Tecnico;
import com.lucasgoiana.helpdesk.enums.Perfil;
import com.lucasgoiana.helpdesk.enums.Prioridade;
import com.lucasgoiana.helpdesk.enums.Status;
import com.lucasgoiana.helpdesk.repositories.ChamadoRepository;
import com.lucasgoiana.helpdesk.repositories.ClienteRepository;
import com.lucasgoiana.helpdesk.repositories.TecnicoRepository;
import org.springframework.stereotype.Service;

@Service
public class DBServiceImpl implements DBService {

    private final TecnicoRepository tecnicoRepository;

    private final ClienteRepository clienteRepository;

    private final ChamadoRepository chamadoRepository;

    DBServiceImpl(TecnicoRepository tecnicoRepository, ClienteRepository clienteRepository, ChamadoRepository chamadoRepository){
        this.tecnicoRepository = tecnicoRepository;
        this.clienteRepository = clienteRepository;
        this.chamadoRepository = chamadoRepository;

        Tecnico tec1 = new Tecnico(null, "Lucas Goiana", "44444444444", "lucasgoianam@hotmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);
        tecnicoRepository.save(tec1);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "11111111111", "linux@hotmail.com", "222");
        clienteRepository.save(cli1);

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado para Bug", "Testei e deu bigode", tec1, cli1);
        chamadoRepository.save(c1);
    }

    @Override
    public void instanciaDB() {System.out.println("Passei aqui");}
}
