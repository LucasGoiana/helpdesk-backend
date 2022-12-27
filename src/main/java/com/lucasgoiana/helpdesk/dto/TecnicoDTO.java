package com.lucasgoiana.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TecnicoDTO {

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Set<String> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO(com.lucasgoiana.helpdesk.entities.Tecnico tecnicoEntity){
        this.id = tecnicoEntity.getId();
        this.nome = tecnicoEntity.getNome();
        this.cpf = tecnicoEntity.getCpf();
        this.email = tecnicoEntity.getEmail();
        this.senha = tecnicoEntity.getSenha();
        this.perfis = tecnicoEntity.getPerfis().stream().map(x -> x.name()).collect(Collectors.toSet());
        this.dataCriacao = tecnicoEntity.getDataCriacao();
     }

//    public static TecnicoDTO converter(Tecnico tecnicoEntity){
//        TecnicoDTO tecnicoDTO = new TecnicoDTO();
//
//        tecnicoDTO.setId(tecnicoEntity.getId());
//        tecnicoDTO.setNome(tecnicoEntity.getNome());
//        tecnicoDTO.setCpf(tecnicoEntity.getCpf());
//        tecnicoDTO.setEmail(tecnicoEntity.getEmail());
//        tecnicoDTO.setSenha(tecnicoEntity.getSenha());
//        tecnicoDTO.setPerfis(tecnicoEntity.getPerfis().stream().map(x -> x.name()).collect(Collectors.toSet()));
//        tecnicoDTO.setDataCriacao(tecnicoEntity.getDataCriacao());
//
//        return tecnicoDTO;
//    }
}
