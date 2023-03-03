package com.example.ListadeTareas.services;

import com.example.ListadeTareas.entities.UserSecurity;
import com.example.ListadeTareas.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    public JpaUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findbyUsername(username)
                .map(UserSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: "));
    }
}
