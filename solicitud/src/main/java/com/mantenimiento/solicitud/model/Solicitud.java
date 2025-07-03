package com.mantenimiento.solicitud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "solicitudes")
public class Solicitud {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El tipo es obligatorio")
    @Size(min = 3, max = 50, message = "El tipo debe tener entre 3 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String tipo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 255, message = "La descripción debe tener entre 10 y 255 caracteres")
    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "Pendiente|En Proceso|Completada",
            message = "El estado debe ser 'Pendiente', 'En Proceso' o 'Completada'")
    @Column(nullable = false, length = 20)
    private String estado;

    @NotBlank(message = "El solicitante es obligatorio")
    @Size(min = 3, max = 100, message = "El solicitante debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String solicitante;

    
}