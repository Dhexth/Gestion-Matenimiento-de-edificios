package com.mantenimiento.proveedores.service;

import com.mantenimiento.proveedores.model.Proveedor;
import com.mantenimiento.proveedores.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public List<Proveedor> obtenerTodos() {
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public List<Proveedor> obtenerPorServicio(String servicio) {
        return proveedorRepository.findByServiciosContaining(servicio);
    }
}