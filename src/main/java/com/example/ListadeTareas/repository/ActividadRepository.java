package com.example.ListadeTareas.repository;

import com.example.ListadeTareas.entities.Actividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActividadRepository extends JpaRepository<Actividades, Long> {
}
