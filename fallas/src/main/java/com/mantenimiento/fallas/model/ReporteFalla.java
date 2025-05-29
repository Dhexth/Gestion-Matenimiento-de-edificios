package com.mantenimiento.fallas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reportes_fallas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteFalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long equipoId;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String prioridad;

    @Column(nullable = false)
    private String estado;

    @Column
    private String solucion;

    @Column(nullable = false)
    private LocalDateTime fechaReporte;

    @Column
    private LocalDateTime fechaResolucion;
}