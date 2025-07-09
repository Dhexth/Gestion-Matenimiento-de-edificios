package com.mantenimiento.fallas.service;

import com.mantenimiento.fallas.model.ReporteFalla;
import com.mantenimiento.fallas.repository.ReporteFallaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteFallaService {

    private final ReporteFallaRepository reporteFallaRepository;

    public List<ReporteFalla> obtenerTodos() {
        return reporteFallaRepository.findAll();
    }

    public ReporteFalla obtenerPorId(Long id) {
        return reporteFallaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte de falla no encontrado"));
    }

    public ReporteFalla guardar(ReporteFalla reporteFalla) {
        reporteFalla.setFechaReporte(LocalDateTime.now());
        reporteFalla.setEstado("Reportado");
        return reporteFallaRepository.save(reporteFalla);
    }

    public ReporteFalla marcarComoResuelto(Long id, String solucion) {
        ReporteFalla reporte = obtenerPorId(id);
        reporte.setEstado("Resuelto");
        reporte.setSolucion(solucion);
        reporte.setFechaResolucion(LocalDateTime.now());
        return reporteFallaRepository.save(reporte);
    }
}