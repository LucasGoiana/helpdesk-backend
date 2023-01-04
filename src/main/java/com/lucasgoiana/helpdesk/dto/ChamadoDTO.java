package com.lucasgoiana.helpdesk.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasgoiana.helpdesk.entities.Chamado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class ChamadoDTO {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    @NotNull(message = "O Campo 'prioridade' é obrigatório ")
    private Integer prioridade;
    @NotNull(message = "O Campo 'status' é obrigatório ")
    private Integer status;
    @NotNull(message = "O Campo 'titulo' é obrigatório ")
    private String titulo;

    @NotNull(message = "O Campo 'observações' é obrigatório ")
    private String observacoes;

    @NotNull(message = "O Campo 'tecnico' é obrigatório ")
    private Integer tecnico;
    @NotNull(message = "O Campo 'cliente' é obrigatório ")
    private Integer cliente;

    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }
}
