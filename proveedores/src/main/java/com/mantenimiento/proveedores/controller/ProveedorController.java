package com.mantenimiento.proveedores.controller;

import com.mantenimiento.proveedores.model.Proveedor;
import com.mantenimiento.proveedores.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerTodos() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.guardar(proveedor));
    }

    @GetMapping("/por-servicio/{servicio}")
    public ResponseEntity<List<Proveedor>> obtenerPorServicio(@PathVariable String servicio) {
        return ResponseEntity.ok(proveedorService.obtenerPorServicio(servicio));
    }
}