package com.mantenimiento.equipos.modulo;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private String estado;

    @Column
    private String modelo;

    @Column
    private String numeroSerie;

    @Column
    private LocalDate fechaInstalacion;

    @Column
    private LocalDate ultimoMantenimiento;
}