package com.phoneshop.controller;

import com.phoneshop.dto.SaleDTO;
import com.phoneshop.model.entity.Product;
import com.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> sale(@RequestBody SaleDTO dto) {

        return null;
    }
}
