package com.aluracursos.foro_hub.controller;

import com.aluracursos.foro_hub.topico.DatosCrearTopico;
import com.aluracursos.foro_hub.topico.DatosRespuestaTopico;
import com.aluracursos.foro_hub.topico.Topico;
import com.aluracursos.foro_hub.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    //Crear tópico (POST)
    @Transactional
    @PostMapping
    public ResponseEntity crear(@RequestBody @Valid DatosCrearTopico datos, UriComponentsBuilder uriComponentsBuilder) {

        var topico = new Topico(datos);// transformamos el DTO en entidad
        repository.save(topico);

        var dto = new DatosRespuestaTopico(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();


        return ResponseEntity.created(uri).body(dto);
    }

    //Listar todos los tópicos (GET /topicos)
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {

        var page = repository.findAll(paginacion).map(topico -> new DatosRespuestaTopico(topico));

        return ResponseEntity.ok(page);
    }

    //Listar un tópico por su id (GET/topicos/{id})
    @GetMapping("/{id}")
    public ResponseEntity obtenerPorId(@PathVariable Long id) {
        var topico = repository.getReferenceById(id); // lanza EntityNotFoundException si no existe
        var dto = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(dto);
    }
}
