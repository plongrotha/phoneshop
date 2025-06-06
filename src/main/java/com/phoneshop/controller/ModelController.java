package com.phoneshop.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phoneshop.dto.ModelDTO;
import com.phoneshop.mapper.ModelMapper;
import com.phoneshop.model.entity.Model;
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
	private final ModelMapper modelMapper;

	@Operation(summary = "Create Model")
	@PostMapping
	public ResponseEntity<?> createModel(@RequestBody @Valid ModelDTO dto) {

		// convert it to model
		Model model = modelMapper.toModel(dto);
		// and then save it
		model = modelService.save(model);

		// and then when return back i convert to DTO
		return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("create model successfully")
				.payload(modelMapper.toModelDTO(model)).status(HttpStatus.CREATED).timestamp(LocalTime.now()).build());
	}

	@Operation(summary = "Get Model by Id")
	@GetMapping("{id}")
	public ResponseEntity<?> getModelById(@RequestParam Integer id) {

		ModelResponse response = modelService.getModelByModelId(id);

		return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("retrieve model successfully")
				.payload(response).status(HttpStatus.OK).timestamp(LocalTime.now()).build());
	}

	@Operation(summary = "Get All model")
	@GetMapping
	public ResponseEntity<?> getAllModel() {
		
		
		List<Model> list = modelService.getAllModels();
		
		List<ModelDTO> dtoList = modelMapper.toListModelDTO(list);
		
		
		return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("retrieve model successfully")
				.payload(dtoList).status(HttpStatus.OK).timestamp(LocalTime.now()).build());
	}

}
