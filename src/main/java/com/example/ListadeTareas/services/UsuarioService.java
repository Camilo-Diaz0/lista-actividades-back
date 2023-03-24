package com.example.ListadeTareas.services;

import com.example.ListadeTareas.dto.AuthRequest;
import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UsuarioService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuariosRepository usuariosRepository;

    public UsuarioService(){}

    public void registrar(Usuarios usuarios){
        usuarios.setPassword(passwordEncoder.encode(usuarios.getPassword()));
        usuarios.setActividades(new ArrayList<>());
        usuariosRepository.save(usuarios);
    }
    public String crearToken(AuthRequest authRequest) throws Exception{
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generarToken(authRequest.getUsername());
        }
        throw new AuthenticationCredentialsNotFoundException("El usuario no esta registrado:");
    }
}
