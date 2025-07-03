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
        
        solicitud = new Solicitud();
        solicitud.setId(1L);
        solicitud.setTipo("Mantenimiento");
        solicitud.setDescripcion("Descripción de prueba");
        solicitud.setFechaSolicitud(LocalDate.now());
        solicitud.setEstado("Pendiente");
        solicitud.setSolicitante("Juan Perez");
    }

    @Test
    void testListarTodas() {
        when(repository.findAll()).thenReturn(Arrays.asList(solicitud));
        
        List<Solicitud> result = service.listarTodas();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    // Resto de los tests...
}