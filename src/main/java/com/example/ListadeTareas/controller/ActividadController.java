package com.example.ListadeTareas.controller;

import com.example.ListadeTareas.entities.Actividades;
import com.example.ListadeTareas.repository.ActividadRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ActividadController {
    private ActividadRepository actividadRepository;
    public ActividadController(ActividadRepository actividadRepository){
        this.actividadRepository = actividadRepository;
    }
    @GetMapping("/api/actividades")
    public ResponseEntity<List<Actividades>> obtener(){
        List<Actividades> listActividades = actividadRepository.findAll();
        if(listActividades.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listActividades);
    }
    @GetMapping("/api/actividades/{id}")
    public ResponseEntity<Actividades> obtenerByID(@PathVariable Long id){
        Optional<Actividades> actividaOpt = actividadRepository.findById(id);
        if (actividaOpt.isPresent()){
            return ResponseEntity.ok(actividaOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/crear")
    public ResponseEntity<Actividades> crear(@RequestBody Actividades actividades){
        if(actividades.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Actividades nuevo = actividadRepository.save(actividades);
        String sId = String.valueOf(actividades.getId());
        return ResponseEntity.created(URI.create("/api/actividades/"+ sId)).body(nuevo);
    }
    @DeleteMapping("/api/actividades/{id}")
    public ResponseEntity<Actividades> eliminarById(@PathVariable Long id){
        Optional<Actividades> actividaOpt = actividadRepository.findById(id);
        if (actividaOpt.isPresent()){
            actividadRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/api/actividades")
    public ResponseEntity<Actividades> actualizar(@RequestBody Actividades actividades){
        if(actividades.getId() == null){
            return ResponseEntity.badRequest().build();
        }else if(!actividadRepository.existsById(actividades.getId())){
            return ResponseEntity.notFound().build();
        }
        Actividades result = actividadRepository.save(actividades);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/api/actividades")
    public ResponseEntity<Actividades> borrarTodo(){
        actividadRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
