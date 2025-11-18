package com.example.Marketing.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record DetectedFaceRequest(
    @NotNull(message = "El ID del análisis visual es obligatorio")
    Integer visualAnalysisId,

    @Size(max = 50, message = "La emoción no puede exceder los 50 caracteres")
    String mainEmotion,

    BigDecimal confidenceScore
) {}