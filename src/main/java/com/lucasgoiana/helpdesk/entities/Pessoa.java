package com.lucasgoiana.helpdesk.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasgoiana.helpdesk.enums.Perfil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String nome;

    @Column(unique = true)
    protected String cpf;

    @Column(unique = true)
    protected String email;

    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name ="Perfis")
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();


    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    public Pessoa() {
        super();addPerfil(Perfil.CLIENTE);
    }



    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum( x )).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfis) {
        this.perfis.add(perfis.getCodigo());
    }

}
