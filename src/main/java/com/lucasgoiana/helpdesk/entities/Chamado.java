package com.lucasgoiana.helpdesk.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasgoiana.helpdesk.dto.ChamadoDTO;
import com.lucasgoiana.helpdesk.dto.ClienteDTO;
import com.lucasgoiana.helpdesk.enums.Perfil;
import com.lucasgoiana.helpdesk.enums.Prioridade;
import com.lucasgoiana.helpdesk.enums.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Chamado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Chamado(){
        super();
    }

    public Chamado(Integer id, Prioridade prioridade, Status status, String titulo, String observacoes, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    public Chamado (Cliente cliente, Tecnico tecnico, ChamadoDTO chamadoDTO){

        this.prioridade = Prioridade.toEnum(chamadoDTO.getPrioridade());
        this.status =Status.toEnum(chamadoDTO.getStatus());
        this.titulo = chamadoDTO.getTitulo();
        this.observacoes = chamadoDTO.getObservacoes();
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

}
