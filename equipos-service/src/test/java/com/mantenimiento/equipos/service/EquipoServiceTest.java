package com.mantenimiento.equipos.service;

import com.mantenimiento.equipos.model.Equipo;
import com.mantenimiento.equipos.repository.EquipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipoServiceTest {

    @InjectMocks
    private EquipoService equipoService;

    @Mock
    private EquipoRepository equipoRepository;

    private Equipo equipo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

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
    void testObtenerTodos() {
        when(equipoRepository.findAll()).thenReturn(List.of(equipo));

        List<Equipo> resultado = equipoService.obtenerTodos();

        assertEquals(1, resultado.size());
        verify(equipoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorIdExistente() {
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));

        Equipo resultado = equipoService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Router Cisco", resultado.getNombre());
        verify(equipoRepository).findById(1L);
    }

    @Test
    void testObtenerPorIdNoExistente() {
        when(equipoRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            equipoService.obtenerPorId(999L);
        });

        assertEquals("Equipo no encontrado", ex.getMessage());
        verify(equipoRepository).findById(999L);
    }

    @Test
    void testGuardar() {
        when(equipoRepository.save(equipo)).thenReturn(equipo);

        Equipo resultado = equipoService.guardar(equipo);

        assertNotNull(resultado);
        assertEquals("Operativo", resultado.getEstado());
        verify(equipoRepository).save(equipo);
    }

    @Test
    void testActualizar() {
        Equipo nuevo = Equipo.builder()
                .nombre("Router Actualizado")
                .ubicacion("Nueva ubicación")
                .estado("En Mantenimiento")
                .build();

        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));
        when(equipoRepository.save(any(Equipo.class))).thenAnswer(invoc -> invoc.getArgument(0));

        Equipo actualizado = equipoService.actualizar(1L, nuevo);

        assertEquals("Router Actualizado", actualizado.getNombre());
        assertEquals("Nueva ubicación", actualizado.getUbicacion());
        assertEquals("En Mantenimiento", actualizado.getEstado());
        verify(equipoRepository).save(any(Equipo.class));
    }

    @Test
    void testEliminar() {
        doNothing().when(equipoRepository).deleteById(1L);

        equipoService.eliminar(1L);

        verify(equipoRepository).deleteById(1L);
    }

    @Test
    void testObtenerPorEstado() {
        when(equipoRepository.findByEstado("Operativo")).thenReturn(List.of(equipo));

        List<Equipo> resultado = equipoService.obtenerPorEstado("Operativo");

        assertFalse(resultado.isEmpty());
        assertEquals("Operativo", resultado.get(0).getEstado());
        verify(equipoRepository).findByEstado("Operativo");
    }
}
