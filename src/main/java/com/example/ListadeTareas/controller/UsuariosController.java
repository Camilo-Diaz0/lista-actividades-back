package com.example.ListadeTareas.controller;

import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuariosController {
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UsuariosController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @PostMapping("/register")
    public ResponseEntity<Usuarios> registrar(@RequestBody Usuarios usuarios){
        if(usuarios.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        usuarios.setPassword(passwordEncoder.encode(usuarios.getPassword()));
        userRepository.save(usuarios);
        return ResponseEntity.ok().build();
    }
}
