package com.mantenimiento.solicitud.model;

import jakarta.persistence.*;
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

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false, length = 100)
    private String solicitante;

    // Métodos de negocio (CRUD)

    /**
     * Crea una nueva solicitud con los datos proporcionados
     */
    public static Solicitud crearSolicitud(String tipo, String descripcion, String solicitante) {
        return Solicitud.builder()
                .tipo(tipo)
                .descripcion(descripcion)
                .fechaSolicitud(LocalDate.now())
                .estado("Pendiente")
                .solicitante(solicitante)
                .build();
    }

    /**
     * Actualiza los campos editables de la solicitud
     */
    public void actualizarDatos(String tipo, String descripcion, String estado) {
        if (tipo != null && !tipo.isBlank()) {
            this.tipo = tipo;
        }
        if (descripcion != null && !descripcion.isBlank()) {
            this.descripcion = descripcion;
        }
        if (estado != null && !estado.isBlank()) {
            this.estado = estado;
        }
    }

    /**
     * Marca la solicitud como completada
     */
    public void marcarComoCompletada() {
        this.estado = "Completada";
    }

    /**
     * Verifica si la solicitud está pendiente
     */
    public boolean estaPendiente() {
        return "Pendiente".equalsIgnoreCase(this.estado);
    }

    /**
     * Verifica si la solicitud pertenece a un solicitante
     */
    public boolean perteneceA(String solicitante) {
        return this.solicitante.equalsIgnoreCase(solicitante);
    }

    /**
     * Método toString personalizado
     */
    @Override
    public String toString() {
        return String.format(
            "Solicitud [ID: %d, Tipo: %s, Solicitante: %s, Estado: %s, Fecha: %s]",
            id, tipo, solicitante, estado, fechaSolicitud
        );
    }
}