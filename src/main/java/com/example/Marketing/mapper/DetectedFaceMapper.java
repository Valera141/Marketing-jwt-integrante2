package com.example.Marketing.mapper;

import com.example.Marketing.dto.DetectedFaceRequest;
import com.example.Marketing.dto.DetectedFaceResponse;
import com.example.Marketing.model.DetectedFace;
import com.example.Marketing.model.VisualAnalysis;

public class DetectedFaceMapper {

    public static DetectedFaceResponse toResponse(DetectedFace entity) {
        if (entity == null) return null;
        return DetectedFaceResponse.builder()
                .detectedFaceId(entity.getDetectedFaceId())
                .visualAnalysisId(entity.getVisualAnalysis() != null ? entity.getVisualAnalysis().getVisualAnalysisId() : null)
                .mainEmotion(entity.getMainEmotion())
                .confidenceScore(entity.getConfidenceScore())
                .build();
    }

    public static DetectedFace toEntity(DetectedFaceRequest request, VisualAnalysis visualAnalysis) {
        if (request == null) return null;
        DetectedFace entity = new DetectedFace();
        entity.setVisualAnalysis(visualAnalysis);
        entity.setMainEmotion(request.mainEmotion());
        entity.setConfidenceScore(request.confidenceScore());
        return entity;
    }

    public static void copyToEntity(DetectedFaceRequest request, DetectedFace entity) {
        if (request == null || entity == null) return;
        entity.setMainEmotion(request.mainEmotion());
        entity.setConfidenceScore(request.confidenceScore());
        // La relación con VisualAnalysis se actualiza en el servicio si es necesario
    }
}