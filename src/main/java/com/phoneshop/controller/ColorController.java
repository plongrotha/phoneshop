package com.phoneshop.controller;

import com.phoneshop.dto.ColorDTO;
import com.phoneshop.mapper.ColorMapper;
import com.phoneshop.model.entity.Color;
import com.phoneshop.model.response.ApiResponse;
import com.phoneshop.service.ColorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/colors")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;
    private final ColorMapper colorMapper;

    @Operation(summary = "Create color", description = "Create a color to database")
    @PostMapping
    public ResponseEntity<?> createColor(@RequestBody @Valid ColorDTO colorDTO) {

        Color color = colorMapper.toColor(colorDTO);
        colorService.createColor(color);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Color created successfully")
                        .status(HttpStatus.CREATED.value())
                        .payload(color)
                        .timestamp(LocalTime.now())
                        .build());
    }

    @Operation(summary = "Get All color")
    @GetMapping
    public ResponseEntity<?> getAllColors() {
        List<Color> colors = colorService.getAllColors();
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Color all got successfully")
                        .status(HttpStatus.OK.value())
                        .payload(colors)
                        .timestamp(LocalTime.now())
                        .build());
    }
}
