package com.mantenimiento.documentos.repository;

import com.mantenimiento.documentos.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByTipo(String tipo);
    List<Documento> findByEquipoId(Long equipoId);
}