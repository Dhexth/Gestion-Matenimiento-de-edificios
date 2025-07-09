package com.mantenimiento.historial.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_mantenimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long equipoId;

    @Column(nullable = false)
    private Long tecnicoId;

    @Column(nullable = false)
    private LocalDateTime fechaIntervencion;

    @Column(nullable = false)
    private String tipoIntervencion; // Mantenimiento, Reparación, Inspección

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Column
    private String partesReemplazadas;

    @Column
    private Double costo;

    @Column(nullable = false)
    private Integer duracionMinutos;
}