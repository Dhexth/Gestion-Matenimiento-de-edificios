package com.mantenimiento.documentos.controller;

import com.mantenimiento.documentos.model.Documento;
import com.mantenimiento.documentos.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @GetMapping
    public ResponseEntity<List<Documento>> obtenerTodos() {
        return ResponseEntity.ok(documentoService.obtenerTodos());
    }

    @GetMapping("/por-tipo/{tipo}")
    public ResponseEntity<List<Documento>> obtenerPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(documentoService.obtenerPorTipo(tipo));
    }

    @PostMapping
    public ResponseEntity<Documento> crear(@RequestBody Documento documento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.guardar(documento));
    }
}