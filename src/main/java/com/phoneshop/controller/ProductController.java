package com.phoneshop.controller;

import com.phoneshop.dto.PriceDTO;
import com.phoneshop.dto.ProductDTO;
import com.phoneshop.dto.ProductImportDTO;
import com.phoneshop.mapper.ProductMapper;
import com.phoneshop.model.entity.Product;
import com.phoneshop.model.response.ApiResponse;
import com.phoneshop.model.response.ProductReponse;
import com.phoneshop.service.ProductService;
import com.phoneshop.utils.DateTimeUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .body(ApiResponse.builder().success(true).message("Product created successfully")
                        .status(HttpStatus.CREATED.value()).payload(product).timestamp(DateTimeUtil.getTime()).build());
    }

    @Cacheable(value = "myCache")
    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductReponse> productResponse = productMapper.toProductReponseList(products);

        return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("Product all get successfully")
                .status(HttpStatus.OK.value()).payload(productResponse).timestamp(DateTimeUtil.getTime()).build());
    }

    @Operation(summary = "Get a product")
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable @Valid Long productId) {
        Product product = productService.getProductById(productId);
        ProductReponse reponse = productMapper.toProductReponse(product);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .success(true)
                        .status(HttpStatus.FOUND.value())
                        .message("get product successfully")
                        .payload(reponse)
                        .timestamp(DateTimeUtil.getTime())
                        .build());
    }

    @Operation(summary = "Import product")
    @PostMapping("/import-product")
    public ResponseEntity<?> importProducts(@RequestBody @Valid ProductImportDTO productImportDTO) {

        productService.importProduct(productImportDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder().success(true).status(HttpStatus.CREATED.value()).message("Product imported successfully")
                        .payload("import " + productImportDTO.getProductId() + " successfully").timestamp(DateTimeUtil.getTime())
                        .build());
    }

    @PostMapping("/{product-id}/setPrice")
    public ResponseEntity<?> setPrice(@Valid Long id, @RequestBody PriceDTO priceDTO) {
        productService.setProductPrice(id, priceDTO.getPrice());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("get product successfully")
                        .payload("successfully")
                        .timestamp(DateTimeUtil.getTime())
                        .build());
    }
}
