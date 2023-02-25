package com.example.ListadeTareas.controller;

import com.example.ListadeTareas.entities.Actividades;
import com.example.ListadeTareas.repository.ActividadRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActividadController {
    private ActividadRepository actividadRepository;
    public ActividadController(ActividadRepository actividadRepository){
        this.actividadRepository = actividadRepository;
    }
    @GetMapping("/api/leer")
    public List<Actividades> obtener(){
        return actividadRepository.findAll();
    }
    @PostMapping("/api/crear")
    public ResponseEntity<Actividades> crear(@RequestBody Actividades actividades){
        if(actividades.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Actividades nuevo = actividadRepository.save(actividades);
        return ResponseEntity.ok().build();

    }

}
