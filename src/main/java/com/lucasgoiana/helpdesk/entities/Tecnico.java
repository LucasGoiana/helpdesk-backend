package com.lucasgoiana.helpdesk.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasgoiana.helpdesk.dto.TecnicoDTO;
import com.lucasgoiana.helpdesk.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Tecnico extends Pessoa implements Serializable {

    @JsonIgnore
    @OneToMany( mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public Tecnico (TecnicoDTO tecnicoDTO){

        this.id = tecnicoDTO.getId();
        this.nome = tecnicoDTO.getNome();
        this.cpf = tecnicoDTO.getCpf();
        this.email = tecnicoDTO.getEmail();
        this.senha = tecnicoDTO.getSenha();
        this.perfis = getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = tecnicoDTO.getDataCriacao();
    }

}
