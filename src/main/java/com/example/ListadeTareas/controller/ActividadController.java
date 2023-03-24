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
import java.net.URISyntaxException;
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
    public ResponseEntity<ArrayList<Actividades>> obtener(){
        String username =SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("nooooooooooooooooooo ssseeeeeeeeeeeeeeeeeeeeeeeeee");
        System.out.println(username);
        Usuarios usuarios = usuariosRepository.findByUsername(username).get();
        ArrayList<Actividades> lista = new ArrayList<>();
        for(Actividades actual : usuarios.getActividades()){
            Actividades actividaTemp = new Actividades(actual.getId(),actual.getActividad(),actual.getHora(),actual.isRealizado(),null);
            lista.add(actividaTemp);
        }
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
        String username = actividades.getUsuario().getUsername();
        Usuarios usuarios = usuariosRepository.findByUsername(username).get();
        actividades.setUsuario(usuarios);
        actividadRepository.save(actividades);
        System.out.println("acaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for(Actividades actual : actividadRepository.findAll()){
            if(actual.getActividad() == actividades.getActividad() && actual.getUsuario() == actividades.getUsuario()){
                System.out.println("aaaaaaaaaaqqqqqqqqqquiiiiiiiiiiiiiii");
                actividades = actual;
                System.out.println(actividades);
                actividades.setUsuario(null);
                System.out.println(actividades);
            }
        }
        return ResponseEntity.created(new URI("/api/actividades")).body(actividades);
    }
    @DeleteMapping("/api/actividades/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> eliminarById(@PathVariable Long id){
        Optional<Actividades> actividadOpt = actividadRepository.findById(id);
        if(actividadOpt.isPresent()){
            Actividades actividades = actividadOpt.get();
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
        Actividades actividadBD = actividadRepository.findById(actividades.getId()).get();
        actividadBD.setActividad(actividades.getActividad());
        actividadBD.setHora(actividades.getHora());
        actividadBD.setRealizado(actividades.isRealizado());
        actividadRepository.save(actividadBD);
        actividadBD.setUsuario(null);
        return ResponseEntity.ok(actividadBD);
    }
    @DeleteMapping("/api/actividades")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Actividades> borrarTodo(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        for(Actividades actual : actividadRepository.findAll()){
            if (Objects.equals(actual.getUsuario().getUsername(), username)){
                actividadRepository.delete(actual);
            }
        }
        return ResponseEntity.ok().build();
    }
}
