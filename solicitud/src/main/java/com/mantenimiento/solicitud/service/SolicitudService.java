package com.mantenimiento.solicitud.service;

import com.mantenimiento.solicitud.model.Solicitud;
import com.mantenimiento.solicitud.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final SolicitudRepository repository;

    public List<Solicitud> listarTodas() {
        return repository.findAll();
    }

    public Solicitud obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Solicitud guardar(Solicitud solicitud) {
        return repository.save(solicitud);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Solicitud> buscarPorEstado(String estado) {
    return repository.findByEstado(estado);
}

public List<Solicitud> buscarPorSolicitante(String solicitante) {
    return repository.findBySolicitanteContainingIgnoreCase(solicitante);
}

public List<Solicitud> buscarPorTipo(String tipo) {
    return repository.findByTipo(tipo);
}

    public Solicitud actualizar(Long id, Solicitud solicitudActualizada) {
        return repository.findById(id)
                .map(solicitudExistente -> {
                    solicitudExistente.setTipo(solicitudActualizada.getTipo());
                    solicitudExistente.setDescripcion(solicitudActualizada.getDescripcion());
                    solicitudExistente.setFechaSolicitud(solicitudActualizada.getFechaSolicitud());
                    solicitudExistente.setEstado(solicitudActualizada.getEstado());
                    solicitudExistente.setSolicitante(solicitudActualizada.getSolicitante());
                    return repository.save(solicitudExistente);
                })
                .orElseGet(() -> {
                    solicitudActualizada.setId(id);
                    return repository.save(solicitudActualizada);
                });
    }
}