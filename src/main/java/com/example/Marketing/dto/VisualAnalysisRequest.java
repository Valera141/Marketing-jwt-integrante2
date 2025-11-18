package com.example.Marketing.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VisualAnalysisRequest(
    /*
    // --- TEMPORALMENTE COMENTADO ---
    @NotNull(message = "El ID del recurso multimedia es obligatorio")
    Integer resourceId,
    */

    @Size(max = 255, message = "El contexto general no puede exceder los 255 caracteres")
    String generalContext,

    @Size(max = 255, message = "El contenido sensible no puede exceder los 255 caracteres")
    String sensitiveContent,

    String ocrText // Sin límite de tamaño por ser TEXT
) {}