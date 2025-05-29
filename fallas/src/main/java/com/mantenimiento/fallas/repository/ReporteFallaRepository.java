package com.mantenimiento.fallas.repository;

import com.mantenimiento.fallas.model.ReporteFalla;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReporteFallaRepository extends JpaRepository<ReporteFalla, Long> {
    List<ReporteFalla> findByEstado(String estado);
    List<ReporteFalla> findByEquipoId(Long equipoId);
}