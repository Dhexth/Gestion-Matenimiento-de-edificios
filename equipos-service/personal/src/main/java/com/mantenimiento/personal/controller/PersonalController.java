package com.mantenimiento.personal.controller;

import com.mantenimiento.personal.model.Personal;
import com.mantenimiento.personal.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/personal")
@RequiredArgsConstructor
public class PersonalController {private final PersonalService personalService;

    @GetMapping
    public ResponseEntity<List<Personal>> obtenerTodos() {
        return ResponseEntity.ok(personalService.obtenerTodos());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Personal>> obtenerDisponibles() {
        return ResponseEntity.ok(personalService.obtenerDisponibles());
    }

    @PostMapping
    public ResponseEntity<Personal> crear(@RequestBody Personal personal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personalService.guardar(personal));
    }
}
    

