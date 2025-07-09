package com.mantenimiento.calendario.controller;

import com.mantenimiento.calendario.model.Evento;
import com.mantenimiento.calendario.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> obtenerTodos() {
        return ResponseEntity.ok(eventoService.obtenerTodos());
    }

    @GetMapping("/entre-fechas")
    public ResponseEntity<List<Evento>> obtenerEntreFechas(
            @RequestParam LocalDate inicio, 
            @RequestParam LocalDate fin) {
        return ResponseEntity.ok(eventoService.obtenerEntreFechas(inicio, fin));
    }

    @PostMapping
    public ResponseEntity<Evento> crear(@RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.guardar(evento));
    }
}