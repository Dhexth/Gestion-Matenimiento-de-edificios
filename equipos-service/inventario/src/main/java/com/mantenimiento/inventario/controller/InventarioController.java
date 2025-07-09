package com.mantenimiento.inventario.controller;

import com.mantenimiento.inventario.model.Inventario;
import com.mantenimiento.inventario.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @GetMapping
    public ResponseEntity<List<Inventario>> getAll() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody Inventario item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
