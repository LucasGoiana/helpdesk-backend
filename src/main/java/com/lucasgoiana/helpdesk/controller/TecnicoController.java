package com.lucasgoiana.helpdesk.controller;

import com.lucasgoiana.helpdesk.dto.TecnicoDTO;
import com.lucasgoiana.helpdesk.entities.Tecnico;
import com.lucasgoiana.helpdesk.service.tecnico.TecnicosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import javax.validation.Valid;
import java.net.URI;
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
        com.lucasgoiana.helpdesk.entities.Tecnico obj = tecnicosService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<com.lucasgoiana.helpdesk.entities.Tecnico> list = tecnicosService.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico newObj = tecnicosService.create(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id , @Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico obj = tecnicosService.update(id, tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable Integer id){
        tecnicosService.delete(id);
    }

}
