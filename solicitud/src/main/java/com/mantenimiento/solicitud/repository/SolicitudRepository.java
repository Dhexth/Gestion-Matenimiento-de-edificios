package com.mantenimiento.solicitud.repository;

import com.mantenimiento.solicitud.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByEstado(String estado);
    List<Solicitud> findBySolicitanteContainingIgnoreCase(String solicitante);
    List<Solicitud> findByTipo(String tipo);
}