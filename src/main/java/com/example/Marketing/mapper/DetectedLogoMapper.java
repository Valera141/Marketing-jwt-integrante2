package com.example.Marketing.mapper;

import com.example.Marketing.dto.DetectedLogoRequest;
import com.example.Marketing.dto.DetectedLogoResponse;
import com.example.Marketing.model.DetectedLogo;
import com.example.Marketing.model.VisualAnalysis;

public class DetectedLogoMapper {

    public static DetectedLogoResponse toResponse(DetectedLogo entity) {
        if (entity == null) return null;
        return DetectedLogoResponse.builder()
                .detectedLogoId(entity.getDetectedLogoId())
                .visualAnalysisId(entity.getVisualAnalysis() != null ? entity.getVisualAnalysis().getVisualAnalysisId() : null)
                .detectedBrand(entity.getDetectedBrand())
                .boundingBoxCoords(entity.getBoundingBoxCoords())
                .confidenceScore(entity.getConfidenceScore())
                .build();
    }

    public static DetectedLogo toEntity(DetectedLogoRequest request, VisualAnalysis visualAnalysis) {
        if (request == null) return null;
        DetectedLogo entity = new DetectedLogo();
        entity.setVisualAnalysis(visualAnalysis);
        entity.setDetectedBrand(request.detectedBrand());
        entity.setBoundingBoxCoords(request.boundingBoxCoords());
        entity.setConfidenceScore(request.confidenceScore());
        return entity;
    }

    public static void copyToEntity(DetectedLogoRequest request, DetectedLogo entity) {
        if (request == null || entity == null) return;
        entity.setDetectedBrand(request.detectedBrand());
        entity.setBoundingBoxCoords(request.boundingBoxCoords());
        entity.setConfidenceScore(request.confidenceScore());
    }
}