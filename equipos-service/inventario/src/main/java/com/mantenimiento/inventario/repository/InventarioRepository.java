package com.mantenimiento.inventario.repository;

import com.mantenimiento.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
