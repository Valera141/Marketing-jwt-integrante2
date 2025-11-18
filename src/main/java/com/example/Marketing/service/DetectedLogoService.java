package com.example.Marketing.service;

import com.example.Marketing.dto.DetectedLogoRequest;
import com.example.Marketing.dto.DetectedLogoResponse;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetectedLogoService {
    Page<DetectedLogoResponse> findAll(Pageable pageable);
    DetectedLogoResponse findById(Integer logoId);
    Page<DetectedLogoResponse> findByVisualAnalysisId(Integer visualAnalysisId, Pageable pageable);
    DetectedLogoResponse create(DetectedLogoRequest request);
    DetectedLogoResponse update(Integer logoId, DetectedLogoRequest request);
    void delete(Integer logoId);
}