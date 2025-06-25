package com.phoneshop.controller;

import com.phoneshop.dto.ModelDTO;
import com.phoneshop.mapper.ModelMapper;
import com.phoneshop.model.entity.Model;
import com.phoneshop.model.response.ApiResponse;
import com.phoneshop.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/models")
@RequiredArgsConstructor
@CrossOrigin
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
                .payload(modelMapper.toModelDTO(model)).status(HttpStatus.CREATED.value()).timestamp(LocalTime.now()).build());
    }

    @Operation(summary = "Get Model by Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getModelById(@PathVariable Long id) {

        Model response = modelService.getModelById(id);

        return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("retrieve model successfully")
                .payload(response).status(HttpStatus.OK.value()).timestamp(LocalTime.now()).build());
    }

    @Operation(summary = "Get All model")
    @GetMapping
    public ResponseEntity<?> getAllModel() {

        List<Model> list = modelService.getAllModels();
        List<ModelDTO> dtoList = modelMapper.toListModelDTO(list);

        return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("retrieve model successfully")
                .payload(list).status(HttpStatus.OK.value()).timestamp(LocalTime.now()).build());
    }

    @GetMapping("/{brand-id}/brand")
    public ResponseEntity<?> getAllByBrandId(@PathVariable("brand-id") Long id) {
        List<Model> model = modelService.getByBrandId(id);

        List<ModelDTO> dtoList = modelMapper.toListModelDTO(model);

        return ResponseEntity.ok().body(ApiResponse.builder().success(true).message("retrieve model successfully")
                .payload(dtoList).status(HttpStatus.OK.value()).timestamp(LocalTime.now()).build());
    }

}
