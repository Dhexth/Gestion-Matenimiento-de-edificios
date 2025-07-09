package com.mantenimiento.proveedores.repository;

import com.mantenimiento.proveedores.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByServiciosContaining(String servicio);
}