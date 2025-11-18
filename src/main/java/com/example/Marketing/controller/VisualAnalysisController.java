package com.example.Marketing.controller;

import com.example.Marketing.dto.VisualAnalysisRequest;
import com.example.Marketing.dto.VisualAnalysisResponse;
import com.example.Marketing.service.VisualAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/visual-analyses")
@RequiredArgsConstructor
@Tag(name = "Visual Analysis", description = "API to manage Visual Analysis results")
public class VisualAnalysisController {

    private final VisualAnalysisService service;

    @Operation(summary = "Create a new visual analysis for a resource")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Analysis created successfully")
    })
    @PostMapping
    public ResponseEntity<VisualAnalysisResponse> create(@Valid @RequestBody VisualAnalysisRequest request) {
        VisualAnalysisResponse createdAnalysis = service.create(request);
        return new ResponseEntity<>(createdAnalysis, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all visual analyses (paginated)")
    @ApiResponse(responseCode = "200", description = "Page of visual analyses successfully retrieved")
    @GetMapping
    public Page<VisualAnalysisResponse> getAllAnalyses(
            @PageableDefault(size = 10, sort = "analysisDate") Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(summary = "Get a visual analysis by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Analysis found",
            content = @Content(schema = @Schema(implementation = VisualAnalysisResponse.class))),
        @ApiResponse(responseCode = "404", description = "Analysis not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VisualAnalysisResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    /*
    // --- TEMPORARILY COMMENTED OUT ---
    // This endpoint directly depends on the findByResourceId method in the service,
    // which was commented out because it requires the relationship with MultimediaResource.
    // Uncomment when the MultimediaResource functionality is active.
    @Operation(summary = "Get a visual analysis by the multimedia resource ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Analysis found"),
        @ApiResponse(responseCode = "404", description = "No analysis found for the specified resource")
    })
    @GetMapping("/resource/{resourceId}")
    public ResponseEntity<VisualAnalysisResponse> getByResourceId(@PathVariable Integer resourceId) {
        return ResponseEntity.ok(service.findByResourceId(resourceId));
    }
    */

    @Operation(summary = "Update an existing visual analysis")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Analysis updated"),
        @ApiResponse(responseCode = "404", description = "Analysis or resource not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VisualAnalysisResponse> update(@PathVariable Integer id, @Valid @RequestBody VisualAnalysisRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Delete a visual analysis")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Analysis deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Analysis not found to delete")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}