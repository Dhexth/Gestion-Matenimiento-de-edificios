package com.mantenimiento.documentos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "documentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String rutaArchivo;

    @Column(nullable = false)
    private LocalDateTime fechaSubida;

    @Column
    private Long equipoId;
}