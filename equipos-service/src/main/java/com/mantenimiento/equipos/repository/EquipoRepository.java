package com.mantenimiento.equipos.repository;

import com.mantenimiento.equipos.modulo.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByEstado(String estado);
}