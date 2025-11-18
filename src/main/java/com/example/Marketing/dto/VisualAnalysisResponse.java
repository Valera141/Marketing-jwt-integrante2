package com.example.Marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class VisualAnalysisResponse {

    @JsonProperty("visual_analysis_id")
    private Integer visualAnalysisId;

    /*
    // --- TEMPORALMENTE COMENTADO ---
    @JsonProperty("resource_id")
    private Integer resourceId;
    */

    @JsonProperty("general_context")
    private String generalContext;

    @JsonProperty("sensitive_content")
    private String sensitiveContent;

    @JsonProperty("ocr_text")
    private String ocrText;

    @JsonProperty("analysis_date")
    private OffsetDateTime analysisDate;

    /*
    // --- TEMPORALMENTE COMENTADO ---
    @JsonProperty("visual_tags")
    private List<VisualTagResponse> visualTags;
    */

    @JsonProperty("detected_logos")
    private List<DetectedLogoResponse> detectedLogos;

    @JsonProperty("detected_faces")
    private List<DetectedFaceResponse> detectedFaces;
}