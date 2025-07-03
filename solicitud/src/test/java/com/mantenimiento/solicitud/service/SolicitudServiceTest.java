package com.mantenimiento.solicitud.service;

import com.mantenimiento.solicitud.model.Solicitud;
import com.mantenimiento.solicitud.repository.SolicitudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SolicitudServiceTest {

    @Mock
    private SolicitudRepository repository;

    @InjectMocks
    private SolicitudService service;

    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        solicitud = Solicitud.builder()
            .id(1L)
            .tipo("Mantenimiento")
            .descripcion("Descripción de prueba")
            .fechaSolicitud(LocalDate.now())
            .estado("Pendiente")
            .solicitante("Juan Perez")
            .build();
    }

    @Test
    void testListarTodas() {
        when(repository.findAll()).thenReturn(Arrays.asList(solicitud));
        
        List<Solicitud> result = service.listarTodas();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testObtenerPorIdExistente() {
        when(repository.findById(1L)).thenReturn(Optional.of(solicitud));
        
        Solicitud result = service.obtenerPorId(1L);
        assertNotNull(result);
        assertEquals("Mantenimiento", result.getTipo());
    }

    @Test
    void testObtenerPorIdNoExistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        
        Solicitud result = service.obtenerPorId(99L);
        assertNull(result);
    }

    @Test
    void testGuardar() {
        when(repository.save(any(Solicitud.class))).thenReturn(solicitud);
        
        Solicitud nueva = Solicitud.builder()
            .tipo("Reparación")
            .descripcion("Nueva descripción")
            .build();
        
        Solicitud result = service.guardar(nueva);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testBuscarPorEstado() {
        when(repository.findByEstado("Pendiente")).thenReturn(Arrays.asList(solicitud));
        
        List<Solicitud> result = service.buscarPorEstado("Pendiente");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Pendiente", result.get(0).getEstado());
    }

    @Test
    void testEliminar() {
        doNothing().when(repository).deleteById(1L);
        
        service.eliminar(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}