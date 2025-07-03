package com.mantenimiento.equipos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantenimiento.equipos.model.Equipo;
import com.mantenimiento.equipos.service.EquipoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(EquipoController.class)
public class EquipoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipoService equipoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Equipo equipo;

    @BeforeEach
    void setUp() {
        equipo = Equipo.builder()
                .id(1L)
                .nombre("Router Cisco")
                .ubicacion("Sala de servidores")
                .estado("Operativo")
                .modelo("RV340")
                .numeroSerie("ABC1234567")
                .fechaInstalacion(LocalDate.of(2023, 3, 15))
                .ultimoMantenimiento(LocalDate.of(2024, 6, 10))
                .build();
    }

    @Test
    void testObtenerTodos() throws Exception {
        when(equipoService.obtenerTodos()).thenReturn(List.of(equipo));

        mockMvc.perform(get("/api/equipos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(equipo.getId()))
                .andExpect(jsonPath("$[0].nombre").value(equipo.getNombre()));
    }

    @Test
    void testObtenerPorId() throws Exception {
        when(equipoService.obtenerPorId(1L)).thenReturn(equipo);

        mockMvc.perform(get("/api/equipos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Router Cisco"))
                .andExpect(jsonPath("$.estado").value("Operativo"));
    }

    @Test
    void testCrear() throws Exception {
        when(equipoService.guardar(any(Equipo.class))).thenReturn(equipo);

        mockMvc.perform(post("/api/equipos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testActualizar() throws Exception {
        when(equipoService.actualizar(eq(1L), any(Equipo.class))).thenReturn(equipo);

        mockMvc.perform(put("/api/equipos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Router Cisco"));
    }

    @Test
    void testEliminar() throws Exception {
        doNothing().when(equipoService).eliminar(1L);

        mockMvc.perform(delete("/api/equipos/1"))
                .andExpect(status().isNoContent());

        verify(equipoService).eliminar(1L);
    }

    @Test
    void testObtenerPorEstado() throws Exception {
        when(equipoService.obtenerPorEstado("Operativo")).thenReturn(List.of(equipo));

        mockMvc.perform(get("/api/equipos/por-estado/Operativo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Operativo"));
    }
}
