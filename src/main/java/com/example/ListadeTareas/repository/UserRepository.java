package com.example.ListadeTareas.repository;

import com.example.ListadeTareas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
