package com.example.Marketing.repository;

import com.example.Marketing.model.VisualAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VisualAnalysisRepository extends JpaRepository<VisualAnalysis, Integer> {
    
    /*
    // --- TEMPORALMENTE COMENTADO ---
    // Métodos que dependen de la relación con MultimediaResource.
    // Descomentar cuando la entidad y la relación estén activas.
    
    // Encuentra un análisis por el ID del recurso multimedia al que está asociado
    Optional<VisualAnalysis> findByResource_ResourceId(Integer resourceId);

    // Verifica si ya existe un análisis para un recurso multimedia
    boolean existsByResource_ResourceId(Integer resourceId);
    */
}