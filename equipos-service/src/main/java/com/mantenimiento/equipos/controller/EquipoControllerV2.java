package com.mantenimiento.equipos.controller;

import com.mantenimiento.equipos.assemblers.EquipoModelAssembler;
import com.mantenimiento.equipos.model.Equipo;
import com.mantenimiento.equipos.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/equipos")
public class EquipoControllerV2 {
   
    @Autowired
    private EquipoService equipoService;

    @Autowired
    private EquipoModelAssembler equipoModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Equipo>> getAllEquipos() {
        List<EntityModel<Equipo>> equipo = equipoService.obtenerTodos().stream()
                .map(equipoModelAssembler::toModel)
                .collect(Collectors.toList());
            
        return CollectionModel.of(equipo,
                linkTo(methodOn(EquipoControllerV2.class).getAllEquipos()).withSelfRel());
}
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Equipo> getEquipoById(@PathVariable Long id) {
        Equipo equipo = equipoService.obtenerPorId(id);
        return equipoModelAssembler.toModel(equipo);
    }

    @GetMapping(value = "/estado/{estado}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Equipo>> getEquiposByEstado(@PathVariable String estado) {
        List<EntityModel<Equipo>> equipos = equipoService.obtenerPorEstado(estado).stream()
                .map(equipoModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(equipos,
                linkTo(methodOn(EquipoControllerV2.class).getEquiposByEstado(estado)).withSelfRel());
    }
    @PostMapping(consumes = MediaTypes.HAL_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Equipo>> createEquipo(@RequestBody Equipo equipo) {
        Equipo savedEquipo = equipoService.guardar(equipo);
        EntityModel<Equipo> entityModel = equipoModelAssembler.toModel(savedEquipo);
        return ResponseEntity
                .created(entityModel.getRequiredLink("self").toUri())
                .body(entityModel);
    }
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Equipo>> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        equipo.setId(id);
        Equipo updatedEquipo = equipoService.guardar(equipo);
        return ResponseEntity
                .ok(equipoModelAssembler.toModel(updatedEquipo));
    }           
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Long id) {
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
