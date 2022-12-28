package com.lucasgoiana.helpdesk.service;

import com.lucasgoiana.helpdesk.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class PessoaService {

    @Autowired
    public PessoaRepository pessoaRepository;

    protected void validaCPFEEmail( Integer id, String cpf, String email){
        var validaCpf = pessoaRepository.findByCpf(cpf);
        var validaEmail = pessoaRepository.findByEmail(email);
        System.out.println("Olá" + validaEmail);
        if (validaCpf.isPresent() && validaCpf.get().getId() !=  id){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF Já Cadastrado!");
        }

        if (validaEmail.isPresent() && validaEmail.get().getId() !=  id){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail Já Cadastrado!");
        }


    }
}
