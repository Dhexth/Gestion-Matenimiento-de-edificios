package com.mantenimiento.historial.service;

import com.mantenimiento.historial.model.Historial;
import com.mantenimiento.historial.repository.HistorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialService {

    private final HistorialRepository historialRepository;

    public List<Historial> obtenerTodo() {
        return historialRepository.findAll();
    }

    public List<Historial> obtenerPorEquipo(Long equipoId) {
        return historialRepository.findByEquipoId(equipoId);
    }

    public List<Historial> obtenerPorTecnico(Long tecnicoId) {
        return historialRepository.findByTecnicoId(tecnicoId);
    }

    public Historial crear(Historial historial) {
        return historialRepository.save(historial);
    }

    public Historial actualizar(Long id, Historial historial) {
        Historial existente = obtenerPorId(id);
        existente.setFechaIntervencion(historial.getFechaIntervencion());
        existente.setDescripcion(historial.getDescripcion());
        existente.setTecnicoId(historial.getTecnicoId());
        existente.setEquipoId(historial.getEquipoId());
        existente.setTipoIntervencion(historial.getTipoIntervencion());
        return historialRepository.save(existente);
    }

    public Historial obtenerPorId(Long id) {
        return historialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de historial no encontrado"));
    }
}