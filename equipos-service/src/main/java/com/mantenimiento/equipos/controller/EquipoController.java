package com.mantenimiento.equipos.controller;

import com.mantenimiento.equipos.model.Equipo;
import com.mantenimiento.equipos.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
@Tag(name = "Equipos", description = "API para el manejo de equipos de mantenimiento")
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    @Operation(summary = "Obtener todos los equipos", 
               description = "Retorna una lista de todos los equipos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de equipos encontrada",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Equipo.class)))
    public ResponseEntity<List<Equipo>> obtenerTodos() {
        return ResponseEntity.ok(equipoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un equipo por ID", 
               description = "Retorna un equipo específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Equipo encontrado",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Equipo.class))),
        @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    public ResponseEntity<Equipo> obtenerPorId(
            @Parameter(description = "ID del equipo a buscar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(equipoService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo equipo", 
               description = "Registra un nuevo equipo en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Equipo creado exitosamente",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Equipo.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<Equipo> crear(
            @Parameter(description = "Datos del equipo a crear")
            @RequestBody Equipo equipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.guardar(equipo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un equipo", 
               description = "Actualiza los datos de un equipo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Equipo actualizado exitosamente",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Equipo.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    public ResponseEntity<Equipo> actualizar(
            @Parameter(description = "ID del equipo a actualizar", example = "1")
            @PathVariable Long id, 
            @Parameter(description = "Datos actualizados del equipo")
            @RequestBody Equipo equipo) {
        return ResponseEntity.ok(equipoService.actualizar(id, equipo));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un equipo", 
               description = "Elimina un equipo del sistema según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Equipo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del equipo a eliminar", example = "1")
            @PathVariable Long id) {
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-estado/{estado}")
    @Operation(summary = "Obtener equipos por estado", 
               description = "Retorna una lista de equipos filtrados por estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de equipos encontrada",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Equipo.class))),
        @ApiResponse(responseCode = "400", description = "Estado no válido")
    })
    public ResponseEntity<List<Equipo>> obtenerPorEstado(
            @Parameter(description = "Estado de los equipos a buscar", example = "activo")
            @PathVariable String estado) {
        return ResponseEntity.ok(equipoService.obtenerPorEstado(estado));
    }
}