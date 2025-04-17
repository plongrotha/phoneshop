package com.phoneshop.org.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.org.dto.BrandDTO;
import com.phoneshop.org.mapper.BrandMapper;
import com.phoneshop.org.model.entity.Brand;
import com.phoneshop.org.model.response.ApiResponse;
import com.phoneshop.org.service.BrandService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

	// inject used constructor
	private final BrandService brandService;

	@Operation(summary = "Create brand", description = "Create a brand to database")
	@PostMapping
	public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.createBrand(brand);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.builder()
        .success(true)
        .message("Brand created successfully")
				.status(HttpStatus.CREATED)
        .payload(BrandMapper.INSTANCE.toBrandDTO(brand))
				.timestemp(LocalTime.now()).build());
	}

  // @Operation(summary = "Get all brands", description = "Get all brands that have in database")  
  // @GetMapping
  // public ResponseEntity<?> getAllBrands(){
  //   List<BrandDTO> brands = brandService.getAllBrands().stream().map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand)).collect(Collectors.toList());
  //   return ResponseEntity.ok(ApiResponse.builder().success(true).message("Brands retrieved successfully")
  //       .payload(brands).status(HttpStatus.OK).timestemp(LocalTime.now()).build());
  // }

	@Operation(summary = "Get all brands by name", description = "Get all brands that have in database")
	@GetMapping("/filter")
	public ResponseEntity<?> getAllBrands(@RequestParam Map<String, String> name) {
		// List<BrandDTO> brands = brandService.getAllBrands(name).stream().map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand)).collect(Collectors.toList());
    return ResponseEntity.ok(ApiResponse.builder()
    .success(true)
    .message("Brands retrieved successfully")
				// .payload(brands)
        .status(HttpStatus.OK)
        .timestemp(LocalTime.now())
        .build());
	}


	@Operation(summary = "Get brand by id", description = "Get brand by id")
	@GetMapping("{brand-id}")
	public ResponseEntity<?> getBrandById(@PathVariable("brand-id") Long id) {
		Brand brand = brandService.getBrandById(id);
		return ResponseEntity.ok(ApiResponse.builder().success(true).message("Brand retrieved successfully")
				.status(HttpStatus.FOUND).payload(brand).timestemp(LocalTime.now()).build());
	}

	@Operation(summary = "Delete brand by id", description = "Delete brand by id")
	@DeleteMapping("{brand-id}")
	public ResponseEntity<?> deleteBrandById(@PathVariable("brand-id") Long id) {
		brandService.deleteBrandById(id);
		return ResponseEntity.ok(ApiResponse.builder().success(true).message("Brand deleted successfully")
				.status(HttpStatus.OK).timestemp(LocalTime.now()).build());
	}

	@Operation(summary = "Update brand by id", description = "Update brand by id")
	@PutMapping("{brand-id}")
	public ResponseEntity<?> updateBrandById(@PathVariable("brand-id") Long id, @RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand.setBrandName(brandDTO.getBrandName());
		brand.setVersion(brandDTO.getVs());
		// brand.setBrandId(id);
		brand = brandService.updateBrandById(id, brand);
		return ResponseEntity.ok(ApiResponse.builder().success(true).status(HttpStatus.OK)
				.message("updated successfully").payload(brand).timestemp(LocalTime.now()).build());
	}


}
