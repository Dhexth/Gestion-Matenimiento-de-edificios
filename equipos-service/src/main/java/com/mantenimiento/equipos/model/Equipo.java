package com.mantenimiento.equipos.model;

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
    
    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String numeroSerie;

    @Column(nullable = false)
    private LocalDate fechaInstalacion;

    @Column(nullable = false)
    private LocalDate ultimoMantenimiento;
}