package com.example.Marketing.controller;

import com.example.Marketing.dto.DetectedFaceRequest;
import com.example.Marketing.dto.DetectedFaceResponse;
import com.example.Marketing.service.DetectedFaceService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/detected-faces")
@RequiredArgsConstructor
@Tag(name = "Detected Faces", description = "API to manage Detected Faces in visual analyses")
public class DetectedFaceController {

    private final DetectedFaceService service;

    @Operation(summary = "Get all detected faces (paginated)")
    @ApiResponse(responseCode = "200", description = "Page of detected faces successfully retrieved")
    @GetMapping
    public Page<DetectedFaceResponse> getAll(
            @PageableDefault(size = 10, sort = "detectedFaceId") Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(summary = "Get a detected face by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Face found",
            content = @Content(schema = @Schema(implementation = DetectedFaceResponse.class))),
        @ApiResponse(responseCode = "404", description = "Face not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetectedFaceResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @Operation(summary = "Get all faces for a specific visual analysis")
    @ApiResponse(responseCode = "200", description = "List of faces for the analysis")
    @GetMapping("/analysis/{visualAnalysisId}")
    public List<DetectedFaceResponse> getByVisualAnalysisId(@PathVariable Integer visualAnalysisId) {
        return service.findByVisualAnalysisId(visualAnalysisId);
    }

    @Operation(summary = "Create a new detected face record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Face record created"),
        @ApiResponse(responseCode = "404", description = "Visual analysis not found")
    })
    @PostMapping
    public ResponseEntity<DetectedFaceResponse> create(@Valid @RequestBody DetectedFaceRequest request) {
        DetectedFaceResponse createdFace = service.create(request);
        return new ResponseEntity<>(createdFace, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a detected face record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Record updated"),
        @ApiResponse(responseCode = "404", description = "Face or visual analysis not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DetectedFaceResponse> update(@PathVariable Integer id, @Valid @RequestBody DetectedFaceRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "Delete a detected face record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Record deleted"),
        @ApiResponse(responseCode = "404", description = "Face not found to delete")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}