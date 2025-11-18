package com.example.Marketing.controller;

import com.example.Marketing.dto.DetectedLogoRequest;
import com.example.Marketing.dto.DetectedLogoResponse;
import com.example.Marketing.service.DetectedLogoService;
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
@RequestMapping("/api/v1/detected-logos")
@RequiredArgsConstructor
@Tag(name = "Detected Logos", description = "API to manage Detected Logos in an analysis")
public class DetectedLogoController {

    private final DetectedLogoService service;

    @Operation(summary = "Get all detected logos (paginated)")
    @ApiResponse(responseCode = "200", description = "Page of detected logos successfully retrieved")
    @GetMapping
    public Page<DetectedLogoResponse> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(summary = "Get a detected logo by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Logo found",
            content = @Content(schema = @Schema(implementation = DetectedLogoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Logo not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetectedLogoResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @Operation(summary = "Get all logos for a specific visual analysis (paginated)")
    @ApiResponse(responseCode = "200", description = "Page of logos for the specified analysis")
    @GetMapping("/analysis/{visualAnalysisId}")
    public Page<DetectedLogoResponse> getByVisualAnalysisId(
            @PathVariable Integer visualAnalysisId,
            @PageableDefault(size = 10) Pageable pageable) {
        return service.findByVisualAnalysisId(visualAnalysisId, pageable);
    }

    @Operation(summary = "Create a new detected logo record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Logo created"),
        @ApiResponse(responseCode = "404", description = "Visual analysis not found")
    })
    @PostMapping
    public ResponseEntity<DetectedLogoResponse> create(@Valid @RequestBody DetectedLogoRequest request) {
        DetectedLogoResponse createdLogo = service.create(request);
        return new ResponseEntity<>(createdLogo, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a detected logo record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Logo updated"),
        @ApiResponse(responseCode = "404", description = "Logo or visual analysis not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DetectedLogoResponse> update(@PathVariable Integer id, @Valid @RequestBody DetectedLogoRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Delete a detected logo record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Logo deleted"),
        @ApiResponse(responseCode = "404", description = "Logo not found to delete")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}