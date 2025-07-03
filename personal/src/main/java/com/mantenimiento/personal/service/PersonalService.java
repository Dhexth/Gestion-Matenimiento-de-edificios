package com.mantenimiento.personal.service;

import com.mantenimiento.personal.model.Personal;
import com.mantenimiento.personal.repository.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor


public class PersonalService {
    
    private final PersonalRepository personalRepository;

    public List<Personal> obtenerTodos() {
        return personalRepository.findAll();
    }

    public List<Personal> obtenerDisponibles() {
        return personalRepository.findByDisponibleTrue();
    }

    public Personal guardar(Personal personal) {
        return personalRepository.save(personal);
    }
}
