package com.lucasgoiana.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasgoiana.helpdesk.entities.Tecnico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TecnicoDTO {

    private Integer id;
    @NotBlank(message = "Campo 'nome' não pode estar em branco")
    @NotNull(message = "Campo 'nome' é obrigatório!!")
    private String nome;
    @NotBlank(message = "Campo 'nome' não pode estar em branco")
    @NotNull(message = "Campo 'cpf' é obrigatório!!")
    private String cpf;
    @NotBlank(message = "Campo 'nome' não pode estar em branco")
    @NotNull(message = "Campo 'email' é obrigatório!!")
    private String email;

    @NotBlank(message = "Campo 'senha' não pode estar em branco")
    @NotNull(message = "Campo 'senha' é obrigatório!!")
    private String senha;
    private Set<String> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO(Tecnico tecnicoEntity){
        this.id = tecnicoEntity.getId();
        this.nome = tecnicoEntity.getNome();
        this.cpf = tecnicoEntity.getCpf();
        this.email = tecnicoEntity.getEmail();
        this.senha = tecnicoEntity.getSenha();
        this.perfis = tecnicoEntity.getPerfis().stream().map(x -> x.name()).collect(Collectors.toSet());
        this.dataCriacao = tecnicoEntity.getDataCriacao();
     }

}
