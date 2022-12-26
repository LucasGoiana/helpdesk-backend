package com.lucasgoiana.helpdesk.controller;

import com.lucasgoiana.helpdesk.entities.Tecnico;
import com.lucasgoiana.helpdesk.service.tecnico.TecnicosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    private final TecnicosService tecnicosService;

    public TecnicoController(TecnicosService tecnicosService) {
        this.tecnicosService = tecnicosService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Tecnico> findById(@PathVariable Integer id){
        Tecnico obj = tecnicosService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
