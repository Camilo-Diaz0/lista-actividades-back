package com.example.ListadeTareas.repository;

import com.example.ListadeTareas.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuarios,Long> {
    Optional<Usuarios> findbyUsername(String username);
}