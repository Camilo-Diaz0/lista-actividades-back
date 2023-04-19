package com.example.ListadeTareas.controller;

import com.example.ListadeTareas.dto.AuthRequest;
import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/crear")
    public ResponseEntity<Usuarios> registrar(@RequestBody Usuarios usuarios){
        if(usuarios.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        try{
            usuarioService.registrar(usuarios);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity<String> autenticarObtenerToken(@RequestBody AuthRequest authRequest){
        try{
            String jwtToken = usuarioService.crearToken(authRequest);
            return ResponseEntity.ok(jwtToken);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

