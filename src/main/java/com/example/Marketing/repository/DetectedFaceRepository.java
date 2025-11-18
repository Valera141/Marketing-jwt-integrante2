package com.example.Marketing.repository;

import com.example.Marketing.model.DetectedFace;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetectedFaceRepository extends JpaRepository<DetectedFace, Integer> {
    
    // Encuentra todas las caras detectadas para un análisis visual específico
    List<DetectedFace> findByVisualAnalysis_VisualAnalysisId(Integer visualAnalysisId);
}