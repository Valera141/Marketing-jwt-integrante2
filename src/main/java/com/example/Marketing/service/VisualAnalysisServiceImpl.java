package com.example.Marketing.service;

import com.example.Marketing.dto.VisualAnalysisRequest;
import com.example.Marketing.dto.VisualAnalysisResponse;
import com.example.Marketing.mapper.VisualAnalysisMapper;
//import com.example.Marketing.model.MultimediaResource;
import com.example.Marketing.model.VisualAnalysis;
//import com.example.Marketing.repository.MultimediaResourceRepository;
import com.example.Marketing.repository.VisualAnalysisRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VisualAnalysisServiceImpl implements VisualAnalysisService {

    private final VisualAnalysisRepository analysisRepository;
    // private final MultimediaResourceRepository resourceRepository; // --- TEMPORALMENTE COMENTADO ---

    @Override
    public VisualAnalysisResponse create(VisualAnalysisRequest request) {
        /*
        // --- BLOQUE TEMPORALMENTE COMENTADO ---
        if (analysisRepository.existsByResource_ResourceId(request.resourceId())) {
            throw new IllegalArgumentException("Ya existe un análisis visual para el recurso con ID: " + request.resourceId());
        }

        MultimediaResource resource = resourceRepository.findById(request.resourceId())
                .orElseThrow(() -> new EntityNotFoundException("Recurso multimedia no encontrado con ID: " + request.resourceId()));
        */
        
        // Se pasa 'null' al mapper ya que la relación está desactivada.
        VisualAnalysis newAnalysis = VisualAnalysisMapper.toEntity(request);
        return VisualAnalysisMapper.toResponse(analysisRepository.save(newAnalysis));
    }

    @Override
    @Transactional(readOnly = true)
    public VisualAnalysisResponse findById(Integer analysisId) {
        return analysisRepository.findById(analysisId)
                .map(VisualAnalysisMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Análisis visual no encontrado con ID: " + analysisId));
    }
    
    /*
    // --- MÉTODO TEMPORALMENTE COMENTADO ---
    @Override
    @Transactional(readOnly = true)
    public VisualAnalysisResponse findByResourceId(Integer resourceId) {
        return analysisRepository.findByResource_ResourceId(resourceId)
                .map(VisualAnalysisMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Análisis visual no encontrado para el recurso con ID: " + resourceId));
    }
    */

    @Override
    public VisualAnalysisResponse update(Integer analysisId, VisualAnalysisRequest request) {
        VisualAnalysis existingAnalysis = analysisRepository.findById(analysisId)
                .orElseThrow(() -> new EntityNotFoundException("Análisis visual no encontrado con ID: " + analysisId));

        /*
        // --- BLOQUE TEMPORALMENTE COMENTADO ---
        if (!existingAnalysis.getResource().getResourceId().equals(request.resourceId())) {
             if (analysisRepository.existsByResource_ResourceId(request.resourceId())) {
                throw new IllegalArgumentException("El nuevo recurso con ID " + request.resourceId() + " ya tiene un análisis asociado.");
            }
            MultimediaResource newResource = resourceRepository.findById(request.resourceId())
                    .orElseThrow(() -> new EntityNotFoundException("Recurso multimedia no encontrado con ID: " + request.resourceId()));
            existingAnalysis.setResource(newResource);
        }
        */
        
        VisualAnalysisMapper.copyToEntity(request, existingAnalysis);
        return VisualAnalysisMapper.toResponse(analysisRepository.save(existingAnalysis));
    }

    @Override
    public void delete(Integer analysisId) {
        if (!analysisRepository.existsById(analysisId)) {
            throw new EntityNotFoundException("Análisis visual no encontrado con ID: " + analysisId);
        }
        analysisRepository.deleteById(analysisId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VisualAnalysisResponse> findAll(Pageable pageable) {
        Page<VisualAnalysis> analysisPage = analysisRepository.findAll(pageable);
        return analysisPage.map(VisualAnalysisMapper::toResponse);
    }
}