package com.example.Marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class DetectedLogoResponse {

    @JsonProperty("identifier logo")
    private Integer detectedLogoId;

    @JsonProperty("visual analysis")
    private Integer visualAnalysisId;

    @JsonProperty("detected brand")
    private String detectedBrand;

    @JsonProperty("bounding box coords")
    private String boundingBoxCoords;

    @JsonProperty("confidence score")
    private BigDecimal confidenceScore;
}