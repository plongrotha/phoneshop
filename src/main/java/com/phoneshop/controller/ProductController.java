package com.phoneshop.controller;

import com.phoneshop.dto.BrandDTO;
import com.phoneshop.dto.ProductDTO;
import com.phoneshop.mapper.BrandMapper;
import com.phoneshop.model.entity.Brand;
import com.phoneshop.model.response.ApiResponse;
import com.phoneshop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create Product", description = "Create a product to database")
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Product created successfully")
                        .status(HttpStatus.CREATED)
                        .payload(null)
                        .timestamp(LocalTime.now())
                        .build());
    }
}
