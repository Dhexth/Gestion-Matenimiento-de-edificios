package com.mantenimiento.equipos.service;

import com.mantenimiento.equipos.model.Equipo;
import com.mantenimiento.equipos.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor    
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public List<Equipo> obtenerTodos() {
        return equipoRepository.findAll();
    }

    public Equipo obtenerPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }

    public Equipo guardar(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo actualizar(Long id, Equipo equipo) {
        Equipo existente = obtenerPorId(id);
        existente.setNombre(equipo.getNombre());
        existente.setUbicacion(equipo.getUbicacion());
        existente.setEstado(equipo.getEstado());
        existente.setModelo(equipo.getModelo());
        existente.setNumeroSerie(equipo.getNumeroSerie());
        existente.setFechaInstalacion(equipo.getFechaInstalacion());
        existente.setUltimoMantenimiento(equipo.getUltimoMantenimiento());
        
        return equipoRepository.save(existente);
    }

    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }

    public List<Equipo> obtenerPorEstado(String estado) {
        return equipoRepository.findByEstado(estado);
    }
}