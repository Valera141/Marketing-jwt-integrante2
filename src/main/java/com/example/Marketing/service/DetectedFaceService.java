package com.example.Marketing.service;

import com.example.Marketing.dto.DetectedFaceRequest;
import com.example.Marketing.dto.DetectedFaceResponse;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetectedFaceService {
    Page<DetectedFaceResponse> findAll(Pageable pageable);
    DetectedFaceResponse findById(Integer faceId);
    List<DetectedFaceResponse> findByVisualAnalysisId(Integer visualAnalysisId);
    DetectedFaceResponse create(DetectedFaceRequest request);
    DetectedFaceResponse update(Integer faceId, DetectedFaceRequest request);
    void delete(Integer faceId);
}