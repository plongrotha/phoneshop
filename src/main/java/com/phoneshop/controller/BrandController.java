package com.phoneshop.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.phoneshop.exception.NotFoundException;
import com.phoneshop.mapper.BrandMapperImpl;
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

import com.phoneshop.dto.BrandDTO;
import com.phoneshop.mapper.BrandMapper;
import com.phoneshop.model.entity.Brand;
import com.phoneshop.model.response.ApiResponse;
import com.phoneshop.service.BrandService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

	// inject used constructor
	private final BrandService brandService;
	private final BrandMapperImpl brandMapperImpl;

	@Operation(summary = "Create brand", description = "Create a brand to database")
	@PostMapping
	public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brandService.createBrand(brand);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.builder()
        .success(true)
        .message("Brand created successfully")
				.status(HttpStatus.CREATED)
        .payload(BrandMapper.INSTANCE.toBrandDTO(brand))
				.timestamp(LocalTime.now()).build());
	}

   @Operation(summary = "Get all brands ", description = "Get all brands that have in database")
   @GetMapping
   public ResponseEntity<?> getAllBrands(){
     List<BrandDTO> brands = brandService.getAllBrands().stream().map(BrandMapper.INSTANCE::toBrandDTO).collect(Collectors.toList());
     return ResponseEntity.ok(
			 ApiResponse.<List<BrandDTO>>builder()
					 .success(true).message("All brands retrieved successfully")
					 .status(HttpStatus.OK)
					 .payload(brands)
					 .timestamp(LocalTime.now()).build()
	 );
   }

	@Operation(summary = "Get brand by id", description = "Get brand by id")
	@GetMapping("{brand-id}")
	public ResponseEntity<?> getBrandById(@PathVariable("brand-id") Long id) {
		Brand brand = brandService.getBrandById(id);
		return ResponseEntity.status(HttpStatus.OK).body(
				ApiResponse.builder()
						.success(true)
						.message("Brand retrieved successfully")
						.status(HttpStatus.OK)
						.payload(brand)
						.timestamp(LocalTime.now())
						.build()
		);
	}

	@Operation(summary = "Delete brand by id", description = "Delete brand by id")
	@DeleteMapping("{brand-id}")
	public ResponseEntity<?> deleteBrandById(@PathVariable("brand-id") @Positive Long id) {
		brandService.deleteBrandById(id);
		return ResponseEntity.ok(ApiResponse.builder().success(true).message("Brand deleted successfully")
				.status(HttpStatus.OK).timestamp(LocalTime.now()).build());
	}

	@Operation(summary = "Update brand by id", description = "Update brand by id")
	@PutMapping("{brand-id}")
	public ResponseEntity<?> updateBrandById(@PathVariable("brand-id") Long id, @RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand.setBrandName(brandDTO.getBrandName());
		brand.setVersion(brandDTO.getVs());
		// brand.setBrandId(id);
//		brand = brandService.updateBrandById(id, brand);
		return ResponseEntity.ok(ApiResponse.builder().success(true).status(HttpStatus.OK)
				.message("updated successfully").payload(brandService.updateBrandById(id, brand)).timestamp(LocalTime.now()).build());
	}
}
