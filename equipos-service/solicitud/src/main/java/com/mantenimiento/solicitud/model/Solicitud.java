package com.mantenimiento.solicitud.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitudes")

public class Solicitud {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;               // Ej: mantenimiento, reparación
    private String descripcion;
    private LocalDate fechaSolicitud;
    private String estado;             // Ej: pendiente, aprobada, rechazada
    private String solicitante;
}

