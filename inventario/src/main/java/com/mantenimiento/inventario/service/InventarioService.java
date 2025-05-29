package com.mantenimiento.inventario.service;

import com.mantenimiento.inventario.model.Inventario;
import com.mantenimiento.inventario.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository repository;

    public List<Inventario> obtenerTodos() {
        return repository.findAll();
    }

    public Inventario obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Inventario guardar(Inventario item) {
        return repository.save(item);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
