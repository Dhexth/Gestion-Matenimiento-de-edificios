package com.mantenimiento.historial.controller;

import com.mantenimiento.historial.model.Historial;
import com.mantenimiento.historial.service.HistorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
public class HistorialController {

    private final HistorialService historialService;

    @GetMapping
    public ResponseEntity<List<Historial>> obtenerTodoHistorial() {
        return ResponseEntity.ok(historialService.obtenerTodo());
    }

    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<Historial>> obtenerPorEquipo(@PathVariable Long equipoId) {
        return ResponseEntity.ok(historialService.obtenerPorEquipo(equipoId));
    }

    @GetMapping("/tecnico/{tecnicoId}")
    public ResponseEntity<List<Historial>> obtenerPorTecnico(@PathVariable Long tecnicoId) {
        return ResponseEntity.ok(historialService.obtenerPorTecnico(tecnicoId));
    }

    @PostMapping
    public ResponseEntity<Historial> crearRegistro(@RequestBody Historial historial) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historialService.crear(historial));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historial> actualizarRegistro(@PathVariable Long id, @RequestBody Historial historial) {
        return ResponseEntity.ok(historialService.actualizar(id, historial));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(historialService.obtenerPorId(id));
    }
}