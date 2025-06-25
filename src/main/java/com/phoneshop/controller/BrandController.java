package com.phoneshop.controller;

import com.phoneshop.dto.BrandDTO;
import com.phoneshop.dto.PageDTO;
import com.phoneshop.mapper.BrandMapper;
import com.phoneshop.model.entity.Brand;
import com.phoneshop.model.response.ApiResponse;
import com.phoneshop.service.BrandService;
import com.phoneshop.utils.DateTimeUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
@CrossOrigin
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "Create brand", description = "Create a brand to database")
    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody @Valid BrandDTO brandDTO) {
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brandService.createBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.builder().success(true).message("Brand created successfully")
                        .status(HttpStatus.CREATED.value()).payload(brand).timestamp(DateTimeUtil.getTime()).build());
    }

    @Operation(summary = "Get brand by id", description = "Get brand by id")
    @GetMapping("{brand-id}")
    public ResponseEntity<?> getBrandById(@PathVariable("brand-id") @Positive Long id) {
        Brand brand = brandService.getBrandById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder().success(true).message("Brand retrieved successfully").status(HttpStatus.FOUND.value())
                        .payload(brand).timestamp(DateTimeUtil.getTime()).build());
    }

    @Operation(summary = "Delete brand by id", description = "Delete brand by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrandById(@PathVariable @Positive Long id) {
        brandService.deleteBrandById(id);

        return ResponseEntity.ok(ApiResponse.builder().success(true).message("Brand deleted successfully")
                .status(HttpStatus.OK.value()).timestamp(DateTimeUtil.getTime()).build());
    }

    @Operation(summary = "Update brand by id", description = "Update brand by id")
    @PutMapping("{brand-id}")
    public ResponseEntity<?> updateBrandById(@PathVariable("brand-id") @Positive Long id,
                                             @RequestBody @Valid BrandDTO brandDTO) {
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand.setBrandName(brandDTO.getBrandName());
        return ResponseEntity
                .ok(ApiResponse.builder().success(true).status(HttpStatus.OK.value()).message("updated successfully")
                        .payload(brandService.updateBrandById(id, brand)).timestamp(DateTimeUtil.getTime()).build());
    }

    @Operation(summary = "Get brand specification")
    @GetMapping("/specification")
    public ResponseEntity<?> getAllBrandSpecification(@RequestParam @Valid Map<String, String> params) {

        Page<Brand> page = brandService.getAllBrandSpecification(params);
        PageDTO pageDTO = new PageDTO(page);

        return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("All brands retrieved successfully")
                .status(HttpStatus.OK.value()).payload(pageDTO).timestamp(DateTimeUtil.getTime()).build());
        
    }

    @GetMapping
    public ResponseEntity<?> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();

        return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("All brands retrieved successfully")
                .status(HttpStatus.OK.value()).payload(brands).timestamp(DateTimeUtil.getTime()).build());
    }
}
