package com.example.ListadeTareas.controller;

import com.example.ListadeTareas.entities.Actividades;
import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/crear")
    public ResponseEntity<Usuarios> registrar(@RequestBody Usuarios usuarios){
        if(usuarios.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        System.out.println(usuarios);
        usuarios.setPassword(passwordEncoder.encode(usuarios.getPassword()));
        usuarios.setActividades(new ArrayList<>());
        System.out.println(usuarios);
        repository.save(usuarios);
        return ResponseEntity.ok().build();
    }
}
