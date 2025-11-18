package com.example.Marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class DetectedFaceResponse {

    @JsonProperty("identifier face")
    private Integer detectedFaceId;

    @JsonProperty("visual analysis")
    private Integer visualAnalysisId;

    @JsonProperty("main emotion")
    private String mainEmotion;

    @JsonProperty("confidence score")
    private BigDecimal confidenceScore;
}