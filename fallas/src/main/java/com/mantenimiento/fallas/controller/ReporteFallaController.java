package com.mantenimiento.fallas.controller;

import com.mantenimiento.fallas.model.ReporteFalla;
import com.mantenimiento.fallas.service.ReporteFallaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes-fallas")
@RequiredArgsConstructor
public class ReporteFallaController {

    private final ReporteFallaService reporteFallaService;

    @GetMapping
    public ResponseEntity<List<ReporteFalla>> obtenerTodos() {
        return ResponseEntity.ok(reporteFallaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteFalla> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reporteFallaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ReporteFalla> crear(@RequestBody ReporteFalla reporteFalla) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteFallaService.guardar(reporteFalla));
    }

    @PutMapping("/{id}/resolver")
    public ResponseEntity<ReporteFalla> marcarComoResuelto(@PathVariable Long id, @RequestBody String solucion) {
        return ResponseEntity.ok(reporteFallaService.marcarComoResuelto(id, solucion));
    }
}