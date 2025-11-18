package com.example.Marketing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "visual_analysis")
public class VisualAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visual_analysis_id")
    private Integer visualAnalysisId;

    /*
    // --- TEMPORALMENTE COMENTADO ---
    // Relación con MultimediaResource. Descomentar cuando la entidad esté lista.
    @OneToOne
    @JoinColumn(name = "resource_id", unique = true, nullable = false)
    private MultimediaResource resource;
    */

    @Column(name = "general_context")
    private String generalContext;

    @Column(name = "sensitive_content")
    private String sensitiveContent;

    @Column(name = "ocr_text", columnDefinition = "TEXT")
    private String ocrText;

    @Column(name = "analysis_date", nullable = false)
    private OffsetDateTime analysisDate = OffsetDateTime.now();

    /*
    // --- TEMPORALMENTE COMENTADO ---
    // Relación con VisualTag. Descomentar cuando la entidad esté lista.
    @OneToMany(mappedBy = "visualAnalysis", cascade = CascadeType.ALL)
    private List<VisualTag> visualTags;
    */

    @OneToMany(mappedBy = "visualAnalysis", cascade = CascadeType.ALL)
    private List<DetectedLogo> detectedLogos;

    @OneToMany(mappedBy = "visualAnalysis", cascade = CascadeType.ALL)
    private List<DetectedFace> detectedFaces;
}