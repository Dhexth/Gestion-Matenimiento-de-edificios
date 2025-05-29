package com.mantenimiento.calendario.repository;

import com.mantenimiento.calendario.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByFechaBetween(LocalDate inicio, LocalDate fin);
    List<Evento> findByTipo(String tipo);
}