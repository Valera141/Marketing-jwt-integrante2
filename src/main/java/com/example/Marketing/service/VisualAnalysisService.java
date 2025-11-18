package com.example.Marketing.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Marketing.dto.VisualAnalysisRequest;
import com.example.Marketing.dto.VisualAnalysisResponse;

public interface VisualAnalysisService {
    VisualAnalysisResponse create(VisualAnalysisRequest request);
    VisualAnalysisResponse findById(Integer analysisId);
    //VisualAnalysisResponse findByResourceId(Integer resourceId); // --- TEMPORALMENTE COMENTADO ---
    VisualAnalysisResponse update(Integer analysisId, VisualAnalysisRequest request);
    void delete(Integer analysisId);
    Page<VisualAnalysisResponse> findAll(Pageable pageable);
}