package com.phoneshop.controller;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.dto.ModelDTO;
import com.phoneshop.model.response.ApiResponse;
import com.phoneshop.model.response.ModelResponse;
import com.phoneshop.service.ModelService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/models")
@RequiredArgsConstructor
public class ModelController {

	private final ModelService modelService;

	@Operation(summary = "Create Model")
	@PostMapping
	public ResponseEntity<?> createModel(@RequestBody @Valid ModelDTO dto) {

		ModelResponse response = modelService.save(dto);

		return ResponseEntity.ok().body(
				ApiResponse.builder().success(true).message("create model successfully").payload(response)
						.status(HttpStatus.CREATED).timestamp(LocalTime.now())
						.build()
		);
	}

}
