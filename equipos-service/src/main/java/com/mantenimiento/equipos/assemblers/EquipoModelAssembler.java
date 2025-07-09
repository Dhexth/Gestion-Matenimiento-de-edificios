package com.mantenimiento.equipos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.mantenimiento.equipos.controller.EquipoControllerV2;
import com.mantenimiento.equipos.model.Equipo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class EquipoModelAssembler implements RepresentationModelAssembler<Equipo, EntityModel<Equipo>> {
    @Override
    @NonNull
    public EntityModel<Equipo> toModel(@NonNull Equipo equipo) {
        return EntityModel.of(
                equipo,
                linkTo(methodOn(EquipoControllerV2.class).getEquipoById(equipo.getId())).withSelfRel(),
                linkTo(methodOn(EquipoControllerV2.class).getAllEquipos()).withRel("equipos"),
                linkTo(methodOn(EquipoControllerV2.class).getEquiposByEstado(equipo.getEstado())).withRel("equiposPorEstado"),
                linkTo(methodOn(EquipoControllerV2.class).createEquipo(equipo)).withRel("crearEquipo"),
                linkTo(methodOn(EquipoControllerV2.class).updateEquipo(equipo.getId(), equipo)).withRel("actualizarEquipo"),
                linkTo(methodOn(EquipoControllerV2.class).deleteEquipo(equipo.getId())).withRel("eliminarEquipo")
        );
    }
}
