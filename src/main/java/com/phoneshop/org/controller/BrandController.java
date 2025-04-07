package com.phoneshop.org.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.org.dto.BrandDTO;
import com.phoneshop.org.model.entity.Brand;
import com.phoneshop.org.model.response.ApiResponse;
import com.phoneshop.org.service.BrandService;
import com.phoneshop.org.service.mapper.Mapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

     // inject used contructor
     private final BrandService brandService;
  
     @PostMapping
     public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO) {
      Brand brand = Mapper.toBrand(brandDTO);
      brand = brandService.createbrand(brand);
      return ResponseEntity.status(HttpStatus.CREATED).body(
        ApiResponse.builder()
        .success(true)
        .message("Brand created successfully")
        .status(HttpStatus.CREATED)
        .payload(Mapper.toBrandDTO(brand))
        .timestemp(LocalTime.now())
        .build()
      );
     }

     @GetMapping
     public ResponseEntity<?> getAllBrands() {
      List<Brand> brands = brandService.getAllBrands();
         return ResponseEntity.ok(
          ApiResponse.builder()
          .success(true)
          .message("Brands retrieved successfully")
          .payload(brands)
          .status(HttpStatus.OK)
          .timestemp(LocalTime.now())
          .build()
         );
     }

     @GetMapping("{brand-id}")
     public ResponseEntity<?> getBrandById(@PathVariable("brand-id")  Long id){
      Brand brand = brandService.getBrandById(id);
       return ResponseEntity.ok(
        ApiResponse.builder()
        .success(true)
        .message("Brand retrieved successfully")
        .status(HttpStatus.FOUND)
        .payload(brand)
        .timestemp(LocalTime.now())
        .build()
       );
     }

     @DeleteMapping("{brand-id}")
     public ResponseEntity<?> deleteBrandById(@PathVariable("brand-id") Long id){
      brandService.deleteBrandById(id);
     return ResponseEntity.ok(
      ApiResponse.builder()
      .success(true)
      .message("Brand deleted successfully")
      .status(HttpStatus.OK)
      .timestemp(LocalTime.now())
      .build()
     );
     }
     
     @PutMapping("{brand-id}")
     public ResponseEntity<?> updateBrandById(@PathVariable("brand-id") Long id,@RequestBody BrandDTO brandDto){
      Brand brand = Mapper.toBrand(brandDto);
      brand = brandService.updateBrand(id, brand);
      return ResponseEntity.ok(
        ApiResponse.builder()
        .success(true)
        .status(HttpStatus.OK)
        .message("updated successfully")
        .payload(brand)
        .timestemp(LocalTime.now())
        .build()
      );
     }
}
