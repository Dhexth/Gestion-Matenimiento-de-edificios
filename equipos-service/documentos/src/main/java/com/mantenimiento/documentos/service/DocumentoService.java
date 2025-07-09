package com.mantenimiento.documentos.service;

import com.mantenimiento.documentos.model.Documento;
import com.mantenimiento.documentos.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }

    public List<Documento> obtenerPorTipo(String tipo) {
        return documentoRepository.findByTipo(tipo);
    }

    public Documento guardar(Documento documento) {
        return documentoRepository.save(documento);
    }
}