package com.example.Marketing.repository;

import com.example.Marketing.model.DetectedLogo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetectedLogoRepository extends JpaRepository<DetectedLogo, Integer> {
    
    // Finds all logos associated with a specific visual analysis
    Page<DetectedLogo> findByVisualAnalysis_VisualAnalysisId(Integer visualAnalysisId, Pageable pageable);
}