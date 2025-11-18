package com.example.Marketing.service;

import com.example.Marketing.dto.DetectedLogoRequest;
import com.example.Marketing.dto.DetectedLogoResponse;
import com.example.Marketing.mapper.DetectedLogoMapper;
import com.example.Marketing.model.DetectedLogo;
import com.example.Marketing.model.VisualAnalysis;
import com.example.Marketing.repository.DetectedLogoRepository;
import com.example.Marketing.repository.VisualAnalysisRepository; // Assuming this repository exists
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
public class DetectedLogoServiceImpl implements DetectedLogoService {

    private final DetectedLogoRepository logoRepository;
    private final VisualAnalysisRepository visualAnalysisRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<DetectedLogoResponse> findAll(Pageable pageable) {
        Page<DetectedLogo> logoPage = logoRepository.findAll(pageable);
        return logoPage.map(DetectedLogoMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public DetectedLogoResponse findById(Integer logoId) {
        return logoRepository.findById(logoId)
                .map(DetectedLogoMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Logo detectado no encontrado con ID: " + logoId));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetectedLogoResponse> findByVisualAnalysisId(Integer visualAnalysisId, Pageable pageable) {
        Page<DetectedLogo> logoPage = logoRepository.findByVisualAnalysis_VisualAnalysisId(visualAnalysisId, pageable);
        return logoPage.map(DetectedLogoMapper::toResponse);
    }

    @Override
    public DetectedLogoResponse create(DetectedLogoRequest request) {
        VisualAnalysis visualAnalysis = visualAnalysisRepository.findById(request.visualAnalysisId())
                .orElseThrow(() -> new EntityNotFoundException("Análisis visual no encontrado con ID: " + request.visualAnalysisId()));
        
        DetectedLogo newLogo = DetectedLogoMapper.toEntity(request, visualAnalysis);
        return DetectedLogoMapper.toResponse(logoRepository.save(newLogo));
    }

    @Override
    public DetectedLogoResponse update(Integer logoId, DetectedLogoRequest request) {
        DetectedLogo existingLogo = logoRepository.findById(logoId)
                .orElseThrow(() -> new EntityNotFoundException("Logo detectado no encontrado con ID: " + logoId));

        DetectedLogoMapper.copyToEntity(request, existingLogo);

        if (!existingLogo.getVisualAnalysis().getVisualAnalysisId().equals(request.visualAnalysisId())) {
            VisualAnalysis newVisualAnalysis = visualAnalysisRepository.findById(request.visualAnalysisId())
                    .orElseThrow(() -> new EntityNotFoundException("Análisis visual no encontrado con ID: " + request.visualAnalysisId()));
            existingLogo.setVisualAnalysis(newVisualAnalysis);
        }

        return DetectedLogoMapper.toResponse(logoRepository.save(existingLogo));
    }

    @Override
    public void delete(Integer logoId) {
        if (!logoRepository.existsById(logoId)) {
            throw new EntityNotFoundException("Logo detectado no encontrado con ID: " + logoId);
        }
        logoRepository.deleteById(logoId);
    }
}