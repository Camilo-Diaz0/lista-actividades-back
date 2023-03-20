package com.example.ListadeTareas.controller;

import com.example.ListadeTareas.entities.Actividades;
import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.repository.ActividadRepository;
import com.example.ListadeTareas.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
public class ActividadController {

    @Autowired
    private UsuariosRepository usuariosRepository;
    private ActividadRepository actividadRepository;

    public ActividadController(ActividadRepository actividadRepository){
        this.actividadRepository = actividadRepository;
    }
    @GetMapping("/api/actividades")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Actividades>> obtener(){
        String username =SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        Usuarios usuarios = usuariosRepository.findByUsername(username).get();
        Long id = usuarios.getId();
        List<Actividades> lista = new ArrayList<>();
        for(Actividades actual : actividadRepository.findAll()){
            if(Objects.equals(actual.getId(), id)){
                lista.add(actual);
            }
        }
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/api/actividades/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> obtenerByID(@PathVariable Long id){
        Optional<Actividades> actividaOpt = actividadRepository.findById(id);
        if (actividaOpt.isPresent()){
            return ResponseEntity.ok(actividaOpt.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/api/crear")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> crear(@RequestBody Actividades actividades){
        if(actividades.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        String username = actividades.getUsuario().getUsername();
        Usuarios usuarios = usuariosRepository.findByUsername(username).get();
        actividades.setUsuario(usuarios);
        actividadRepository.save(actividades);
        String sId = String.valueOf(actividades.getId());
        System.out.println(sId);
//        return ResponseEntity.created(URI.create("/api/actividades/"+ sId)).body(actividades);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/api/actividades/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> eliminarById(@PathVariable Long id){
        Optional<Actividades> actividaOpt = actividadRepository.findById(id);
        if (actividaOpt.isPresent()){
            actividadRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/api/actividades")
    @PreAuthorize("hasAuthority('ROLE_USER')")
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
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> borrarTodo(){
        actividadRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
