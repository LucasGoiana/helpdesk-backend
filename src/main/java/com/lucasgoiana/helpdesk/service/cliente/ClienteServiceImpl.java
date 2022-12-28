package com.lucasgoiana.helpdesk.service.cliente;

import com.lucasgoiana.helpdesk.dto.ClienteDTO;
import com.lucasgoiana.helpdesk.entities.Cliente;
import com.lucasgoiana.helpdesk.entities.Tecnico;
import com.lucasgoiana.helpdesk.repositories.ClienteRepository;
import com.lucasgoiana.helpdesk.service.PessoaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteServiceImpl extends PessoaService implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente findById(Integer id) {
        var obj = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não Encontrado"));
        return obj;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> obj = clienteRepository.findAll();
        return obj;
    }

    @Override
    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        validaCPFEEmail(clienteDTO.getId(), clienteDTO.getCpf(), clienteDTO.getEmail());
        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Integer id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente oldObj = findById(id);
        validaCPFEEmail(clienteDTO.getId(), clienteDTO.getCpf(), clienteDTO.getEmail());
        oldObj = new Cliente(clienteDTO);
        return clienteRepository.save(oldObj);
    }

    @Override
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Existe Chamados associado a esse Cliente");
        }

        clienteRepository.deleteById(id);
    }

}
