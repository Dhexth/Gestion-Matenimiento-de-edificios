package com.mantenimiento.calendario.service;

import com.mantenimiento.calendario.model.Evento;
import com.mantenimiento.calendario.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
    }

    public List<Evento> obtenerEntreFechas(LocalDate inicio, LocalDate fin) {
        return eventoRepository.findByFechaBetween(inicio, fin);
    }

    public Evento guardar(Evento evento) {
        return eventoRepository.save(evento);
    }
}