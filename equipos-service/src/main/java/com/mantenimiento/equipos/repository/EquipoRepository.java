package com.mantenimiento.equipos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mantenimiento.equipos.model.Equipo;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByEstado(String estado);
}