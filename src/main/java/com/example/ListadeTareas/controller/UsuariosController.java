package com.example.ListadeTareas.controller;

import com.example.ListadeTareas.dto.AuthRequest;
import com.example.ListadeTareas.entities.Actividades;
import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.repository.UsuariosRepository;
import com.example.ListadeTareas.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsuariosRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

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

    @PostMapping("/autenticar")
    public String autenticarObtenerToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generarToken(authRequest.getUsername());
        }
        return "no registrado";
    }
}

