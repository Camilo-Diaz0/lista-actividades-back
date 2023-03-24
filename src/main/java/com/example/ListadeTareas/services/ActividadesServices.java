package com.example.ListadeTareas.services;

import com.example.ListadeTareas.dto.AuthRequest;
import com.example.ListadeTareas.entities.Actividades;
import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.repository.ActividadRepository;
import com.example.ListadeTareas.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ActividadesServices {
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private ActividadRepository actividadRepository;

    public ActividadesServices() {
    }
    public void borrarActividades(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        for(Actividades actual : actividadRepository.findAll()){
            if (Objects.equals(actual.getUsuario().getUsername(), username)){
                actividadRepository.delete(actual);
            }
        }
    }
    public Actividades CrearActividad(Actividades actividad){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuarios usuarios = usuariosRepository.findByUsername(username).get();
        actividad.setUsuario(usuarios);
        actividadRepository.save(actividad);
        for(Actividades actual : actividadRepository.findAll()){
            if(actual.getActividad() == actividad.getActividad() && actual.getUsuario() == actividad.getUsuario()){
                actividad = actual;
                actividad.setUsuario(null);
            }
        }
        return actividad;
    }
    public List<Actividades> listaActividades(){
        String username =SecurityContextHolder.getContext().getAuthentication().getName();
        Usuarios usuarios = usuariosRepository.findByUsername(username).get();
        for(Actividades actual : usuarios.getActividades()){
            actual.setUsuario(null);
        }
        return usuarios.getActividades();
    }
    public Actividades actualizarActividad(Actividades actividades) throws Exception {
        if(!actividadRepository.existsById(actividades.getId())){
            throw new ChangeSetPersister.NotFoundException();
        }
        Actividades actividadBD = actividadRepository.findById(actividades.getId()).get();
        actividadBD.setActividad(actividades.getActividad());
        actividadBD.setHora(actividades.getHora());
        actividadBD.setRealizado(actividades.isRealizado());
        actividadRepository.save(actividadBD);
        actividadBD.setUsuario(null);
        return actividadBD;
    }
    public void eliminarByID(Long id) throws Exception{
        Optional<Actividades> actividadOpt = actividadRepository.findById(id);
        if(actividadOpt.isPresent()) {
            Actividades actividades = actividadOpt.get();
            actividadRepository.deleteById(id);
            return;
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
