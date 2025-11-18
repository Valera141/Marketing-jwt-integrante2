package com.example.Marketing.service;

import com.example.Marketing.dto.DetectedFaceRequest;
import com.example.Marketing.dto.DetectedFaceResponse;
import com.example.Marketing.mapper.DetectedFaceMapper;
import com.example.Marketing.model.DetectedFace;
import com.example.Marketing.model.VisualAnalysis;
import com.example.Marketing.repository.DetectedFaceRepository;
import com.example.Marketing.repository.VisualAnalysisRepository; // Asumo que este repositorio existe
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DetectedFaceServiceImpl implements DetectedFaceService {

    private final DetectedFaceRepository faceRepository;
    private final VisualAnalysisRepository visualAnalysisRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<DetectedFaceResponse> findAll(Pageable pageable) {
        Page<DetectedFace> facePage = faceRepository.findAll(pageable);
        return facePage.map(DetectedFaceMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public DetectedFaceResponse findById(Integer faceId) {
        return faceRepository.findById(faceId)
                .map(DetectedFaceMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Cara detectada no encontrada con ID: " + faceId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetectedFaceResponse> findByVisualAnalysisId(Integer visualAnalysisId) {
        return faceRepository.findByVisualAnalysis_VisualAnalysisId(visualAnalysisId).stream()
                .map(DetectedFaceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DetectedFaceResponse create(DetectedFaceRequest request) {
        VisualAnalysis visualAnalysis = visualAnalysisRepository.findById(request.visualAnalysisId())
                .orElseThrow(() -> new EntityNotFoundException("Análisis visual no encontrado con ID: " + request.visualAnalysisId()));
        
        DetectedFace newFace = DetectedFaceMapper.toEntity(request, visualAnalysis);
        return DetectedFaceMapper.toResponse(faceRepository.save(newFace));
    }

    @Override
    public DetectedFaceResponse update(Integer faceId, DetectedFaceRequest request) {
        DetectedFace existingFace = faceRepository.findById(faceId)
                .orElseThrow(() -> new EntityNotFoundException("Cara detectada no encontrada con ID: " + faceId));

        DetectedFaceMapper.copyToEntity(request, existingFace);

        // Si el ID del análisis visual cambia, actualiza la relación
        if (!existingFace.getVisualAnalysis().getVisualAnalysisId().equals(request.visualAnalysisId())) {
            VisualAnalysis newVisualAnalysis = visualAnalysisRepository.findById(request.visualAnalysisId())
                    .orElseThrow(() -> new EntityNotFoundException("Análisis visual no encontrado con ID: " + request.visualAnalysisId()));
            existingFace.setVisualAnalysis(newVisualAnalysis);
        }

        return DetectedFaceMapper.toResponse(faceRepository.save(existingFace));
    }

    @Override
    public void delete(Integer faceId) {
        if (!faceRepository.existsById(faceId)) {
            throw new EntityNotFoundException("Cara detectada no encontrada con ID: " + faceId);
        }
        faceRepository.deleteById(faceId);
    }
}