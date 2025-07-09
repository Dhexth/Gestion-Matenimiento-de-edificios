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
}