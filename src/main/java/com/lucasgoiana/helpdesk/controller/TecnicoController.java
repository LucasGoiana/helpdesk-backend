package com.lucasgoiana.helpdesk.controller;

import com.lucasgoiana.helpdesk.dto.TecnicoDTO;
import com.lucasgoiana.helpdesk.entities.Tecnico;
import com.lucasgoiana.helpdesk.service.tecnico.TecnicosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    private final TecnicosService tecnicosService;

    public TecnicoController(TecnicosService tecnicosService) {
        this.tecnicosService = tecnicosService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico obj = tecnicosService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = tecnicosService.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

}
