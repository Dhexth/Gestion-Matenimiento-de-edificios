package com.mantenimiento.solicitud.controller;

import com.mantenimiento.solicitud.model.Solicitud;
import com.mantenimiento.solicitud.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService service;

    @GetMapping("/estado/{estado}")
public ResponseEntity<List<Solicitud>> getByEstado(@PathVariable String estado) {
    return ResponseEntity.ok(service.buscarPorEstado(estado));
}

@GetMapping("/solicitante/{nombre}")
public ResponseEntity<List<Solicitud>> getBySolicitante(@PathVariable String nombre) {
    return ResponseEntity.ok(service.buscarPorSolicitante(nombre));
}

@GetMapping("/tipo/{tipo}")
public ResponseEntity<List<Solicitud>> getByTipo(@PathVariable String tipo) {
    return ResponseEntity.ok(service.buscarPorTipo(tipo));
}
    @PostMapping
    public ResponseEntity<Solicitud> crear(@RequestBody Solicitud solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(solicitud));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(
            @PathVariable Long id,
            @RequestBody Solicitud solicitud) {
        return ResponseEntity.ok(service.actualizar(id, solicitud));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}