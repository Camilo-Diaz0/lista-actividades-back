package com.example.ListadeTareas.controller;

import com.example.ListadeTareas.entities.Actividades;
import com.example.ListadeTareas.services.ActividadesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class ActividadController {

    @Autowired
    private ActividadesServices actividadesServices;
    public ActividadController(){}

    @GetMapping("/api/pruebas")
    public String pruebita(){
        return "que pasa puto";
    }

    @GetMapping("/api/actividades")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Actividades>> obtener(){
        List<Actividades> lista = actividadesServices.listaActividades();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/api/crear")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> crear(@RequestBody Actividades actividades) throws URISyntaxException {
        if(actividades.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        actividades = actividadesServices.CrearActividad(actividades);
        return ResponseEntity.created(new URI("/api/actividades")).body(actividades);
    }

    @DeleteMapping("/api/actividades/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> eliminarById(@PathVariable Long id) {
        try{
            actividadesServices.eliminarByID(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/actividades")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> actualizar(@RequestBody Actividades actividades){
        if(actividades.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        try {
            actividades = actividadesServices.actualizarActividad(actividades);
            return ResponseEntity.ok(actividades);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/actividades")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> borrarTodo(){
        actividadesServices.borrarActividades();
        return ResponseEntity.ok().build();
    }
}
