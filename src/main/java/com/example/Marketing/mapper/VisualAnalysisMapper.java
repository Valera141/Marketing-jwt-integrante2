package com.example.Marketing.mapper;

import com.example.Marketing.dto.DetectedFaceResponse;
import com.example.Marketing.dto.DetectedLogoResponse;
import com.example.Marketing.dto.VisualAnalysisRequest;
import com.example.Marketing.dto.VisualAnalysisResponse;
//import com.example.Marketing.dto.VisualTagResponse;
//import com.example.Marketing.model.MultimediaResource;
import com.example.Marketing.model.VisualAnalysis;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

public class VisualAnalysisMapper {

    public static VisualAnalysisResponse toResponse(VisualAnalysis entity) {
        if (entity == null) return null;
        
        /*
        // --- TEMPORALMENTE COMENTADO ---
        var visualTags = (entity.getVisualTags() != null)
                ? entity.getVisualTags().stream().map(VisualTagMapper::toResponse).collect(Collectors.toList())
                : Collections.<VisualTagResponse>emptyList();
        */
        
        var detectedLogos = (entity.getDetectedLogos() != null)
                ? entity.getDetectedLogos().stream().map(DetectedLogoMapper::toResponse).collect(Collectors.toList())
                : Collections.<DetectedLogoResponse>emptyList();

        var detectedFaces = (entity.getDetectedFaces() != null)
                ? entity.getDetectedFaces().stream().map(DetectedFaceMapper::toResponse).collect(Collectors.toList())
                : Collections.<DetectedFaceResponse>emptyList();

        return VisualAnalysisResponse.builder()
                .visualAnalysisId(entity.getVisualAnalysisId())
                // .resourceId(entity.getResource() != null ? entity.getResource().getResourceId() : null) // --- TEMPORALMENTE COMENTADO ---
                .generalContext(entity.getGeneralContext())
                .sensitiveContent(entity.getSensitiveContent())
                .ocrText(entity.getOcrText())
                .analysisDate(entity.getAnalysisDate())
                // .visualTags(visualTags) // --- TEMPORALMENTE COMENTADO ---
                .detectedLogos(detectedLogos)
                .detectedFaces(detectedFaces)
                .build();
    }

    // El parámetro 'resource' será null por ahora.
    public static VisualAnalysis toEntity(VisualAnalysisRequest request) {
        if (request == null) return null;

        VisualAnalysis entity = new VisualAnalysis();
        // entity.setResource(resource); // --- TEMPORALMENTE COMENTADO ---
        entity.setGeneralContext(request.generalContext());
        entity.setSensitiveContent(request.sensitiveContent());
        entity.setOcrText(request.ocrText());
        entity.setAnalysisDate(OffsetDateTime.now());
        
        return entity;
    }

    public static void copyToEntity(VisualAnalysisRequest request, VisualAnalysis entity) {
        if (request == null || entity == null) return;
        
        entity.setGeneralContext(request.generalContext());
        entity.setSensitiveContent(request.sensitiveContent());
        entity.setOcrText(request.ocrText());
    }
}