package com.phoneshop.controller;

import com.phoneshop.dto.ProductDTO;
import com.phoneshop.mapper.ProductMapper;
import com.phoneshop.model.entity.Product;
import com.phoneshop.model.response.ApiResponse;
import com.phoneshop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Create Product", description = "Create a product to database")
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDTO productDTO) {

        Product product = productMapper.toProduct(productDTO);
        productService.saveProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Product created successfully")
                        .status(HttpStatus.CREATED.value())
                        .payload(product)
                        .timestamp(LocalTime.now())
                        .build());
    }


    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                        .success(true)
                        .message("Product all get successfully")
                        .status(HttpStatus.OK.value())
                        .payload(products)
                        .timestamp(LocalTime.now())
                        .build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable @Valid Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .success(true)
                        .message("get product successfully")
                        .status(HttpStatus.FOUND.value())
                        .payload(product)
                        .timestamp(LocalTime.now())
                        .build());
    }
}
