package com.example.Marketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record DetectedLogoRequest(
    @NotNull(message = "El ID del análisis visual es obligatorio")
    Integer visualAnalysisId,

    @NotBlank(message = "La marca detectada no puede estar vacía")
    @Size(max = 100, message = "La marca no puede exceder los 100 caracteres")
    String detectedBrand,

    @Size(max = 100, message = "Las coordenadas no pueden exceder los 100 caracteres")
    String boundingBoxCoords,

    BigDecimal confidenceScore
) {}