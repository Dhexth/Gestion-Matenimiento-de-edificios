package com.mantenimiento.personal.repository;

import com.mantenimiento.personal.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
    List<Personal> findByDisponibleTrue();
    List<Personal> findByEspecialidad(String especialidad);
}