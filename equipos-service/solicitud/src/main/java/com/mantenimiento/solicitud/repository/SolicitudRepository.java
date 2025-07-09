package com.mantenimiento.solicitud.repository;

import com.mantenimiento.solicitud.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
}