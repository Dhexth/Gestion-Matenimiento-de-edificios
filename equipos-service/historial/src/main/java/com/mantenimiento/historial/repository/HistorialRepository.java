package com.mantenimiento.historial.repository;

import com.mantenimiento.historial.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
    List<Historial> findByEquipoId(Long equipoId);
    List<Historial> findByTecnicoId(Long tecnicoId);
    List<Historial> findByTipoIntervencion(String tipoIntervencion);
}