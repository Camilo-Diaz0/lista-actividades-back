package com.example.ListadeTareas.services;

import com.example.ListadeTareas.entities.UserSecurity;
import com.example.ListadeTareas.entities.Usuarios;
import com.example.ListadeTareas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class JpaUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuarios> usuario = userRepository.findByUsername(username);
        return usuario.map(UserSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("user no encontrado; " + username));
    }
}
