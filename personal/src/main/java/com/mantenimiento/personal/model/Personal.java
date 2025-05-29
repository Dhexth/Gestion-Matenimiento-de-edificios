package com.mantenimiento.personal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false)
    private boolean disponible;


    
}
