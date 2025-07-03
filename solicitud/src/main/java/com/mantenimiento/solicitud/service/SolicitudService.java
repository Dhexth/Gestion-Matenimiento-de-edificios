package com.mantenimiento.solicitud.service;

import com.mantenimiento.solicitud.model.Solicitud;
import com.mantenimiento.solicitud.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SolicitudService {

    private final SolicitudRepository repository;

    @Transactional(readOnly = true)
    public List<Solicitud> listarTodas() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Solicitud obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Solicitud guardar(Solicitud solicitud) {
        if (solicitud.getEstado() == null) {
            solicitud.setEstado("Pendiente");
        }
        if (solicitud.getFechaSolicitud() == null) {
            solicitud.setFechaSolicitud(java.time.LocalDate.now());
        }
        return repository.save(solicitud);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Solicitud> buscarPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    @Transactional(readOnly = true)
    public List<Solicitud> buscarPorSolicitante(String solicitante) {
        return repository.findBySolicitanteContainingIgnoreCase(solicitante);
    }

    @Transactional(readOnly = true)
    public List<Solicitud> buscarPorTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public Solicitud actualizar(Long id, Solicitud solicitudActualizada) {
        return repository.findById(id)
                .map(existente -> {
                    if (solicitudActualizada.getTipo() != null) {
                        existente.setTipo(solicitudActualizada.getTipo());
                    }
                    if (solicitudActualizada.getDescripcion() != null) {
                        existente.setDescripcion(solicitudActualizada.getDescripcion());
                    }
                    if (solicitudActualizada.getEstado() != null) {
                        existente.setEstado(solicitudActualizada.getEstado());
                    }
                    if (solicitudActualizada.getSolicitante() != null) {
                        existente.setSolicitante(solicitudActualizada.getSolicitante());
                    }
                    return repository.save(existente);
                })
                .orElseGet(() -> {
                    solicitudActualizada.setId(id);
                    return repository.save(solicitudActualizada);
                });
    }
}