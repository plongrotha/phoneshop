package com.phoneshop.service.impl;

import org.springframework.stereotype.Service;

import com.phoneshop.dto.ModelDTO;
import com.phoneshop.exception.NotFoundException;
import com.phoneshop.model.entity.Brand;
import com.phoneshop.model.entity.Model;
import com.phoneshop.model.response.ModelResponse;
import com.phoneshop.repository.BrandRepository;
import com.phoneshop.repository.ModelRepository;
import com.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

	private final ModelRepository modelRepository;
	private final BrandRepository brandRepository;

	@Override
	public ModelResponse save(ModelDTO dto) {
		
		Model model = new Model();
		
		Brand brand = brandRepository.findById(Long.valueOf(dto.getBrandId()))
				.orElseThrow(() -> new NotFoundException("Brand id "+ dto.getBrandId() +" not found."));
		
		model.setBrand(brand);
		model.setModelName(dto.getModelName());
		model.setVersion(dto.getVersion());
		
		log.info("create model {} : " + model);
		model = modelRepository.save(model);
		
		ModelResponse response = new ModelResponse();
		response.setModelId(model.getModelId());
		response.setBrandId(model.getBrand().getBrandId());
		response.setName(model.getModelName());
		response.setVersion(model.getVersion());
		
		return response;
	}

	@Override
	public ModelResponse getModelByModelId(Integer id) {
		
		Model model = modelRepository.findById(id).orElseThrow(() -> new NotFoundException("model id " + id + " not found."));
		
		ModelResponse response = new ModelResponse();
		response.setBrandId(model.getBrand().getBrandId());
		response.setModelId(model.getModelId());
		response.setName(model.getModelName());
		response.setVersion(model.getVersion());
		
		return response;
		
	}


}
