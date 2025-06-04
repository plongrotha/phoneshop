package com.phoneshop.controller;

import java.time.LocalTime;
import java.util.Map;

import com.phoneshop.dto.PageDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

	@Operation(summary = "Create brand", description = "Create a brand to database")
	@PostMapping
	public ResponseEntity<?> createBrand(@RequestBody @Valid BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brandService.createBrand(brand);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.builder()
                .success(true)
                .message("Brand created successfully")
				.status(HttpStatus.CREATED)
                .payload(brand)
				.timestamp(LocalTime.now()).build());
	}

	@Operation(summary = "Get brand by id", description = "Get brand by id")
	@GetMapping("{brand-id}")
	public ResponseEntity<?> getBrandById(@PathVariable("brand-id") @Positive Long id) {
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
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteBrandById(@PathVariable("id") @Positive Long id) {
		brandService.deleteBrandById(id);
		return ResponseEntity.ok(ApiResponse.builder().success(true).message("Brand deleted successfully")
				.status(HttpStatus.OK).timestamp(LocalTime.now()).build());
	}

	@Operation(summary = "Update brand by id", description = "Update brand by id")
	@PutMapping("{brand-id}")
	public ResponseEntity<?> updateBrandById(@PathVariable("brand-id") @Positive Long id, @RequestBody @Valid BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand.setBrandName(brandDTO.getBrandName());
		brand.setVs(brandDTO.getVersion());
		// brand.setBrandId(id);
//		brand = brandService.updateBrandById(id, brand);
		return ResponseEntity.ok(ApiResponse.builder().success(true).status(HttpStatus.OK)
				.message("updated successfully").payload(brandService.updateBrandById(id, brand)).timestamp(LocalTime.now()).build());
	}


	@GetMapping
	public ResponseEntity<?> getAllBrandSpecification(@RequestParam @Valid Map<String, String> params){

//		List<BrandDTO> list = brandService.getAllBrandSpecification(params)
//				.stream().map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
//				.collect(Collectors.toList());

		Page<Brand> page = brandService.getAllBrandSpecification(params);
		PageDTO pageDTO = new PageDTO(page);

		return ResponseEntity.ok().body(
				ApiResponse.builder()
				.success(true)
				.message("All brands retrieved successfully")
				.status(HttpStatus.OK)
				.payload(pageDTO)
				.timestamp(LocalTime.now()).build()
		);
	}
}
