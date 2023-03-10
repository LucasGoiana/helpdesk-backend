package com.lucasgoiana.helpdesk.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasgoiana.helpdesk.dto.ClienteDTO;
import com.lucasgoiana.helpdesk.dto.TecnicoDTO;
import com.lucasgoiana.helpdesk.enums.Perfil;
import lombok.EqualsAndHashCode;
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
public class Cliente extends Pessoa implements Serializable
{

    @JsonIgnore
    @OneToMany( mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }
    public Cliente (ClienteDTO clienteDTO){
        super(Perfil.CLIENTE.getCodigo());
        this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
        this.email = clienteDTO.getEmail();
        this.senha = clienteDTO.getSenha();
        this.perfis = getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = clienteDTO.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
