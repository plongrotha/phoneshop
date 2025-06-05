package com.phoneshop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.phoneshop.dto.ModelDTO;
import com.phoneshop.exception.NotFoundException;
import com.phoneshop.mapper.ModelMapper;
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
	private final ModelMapper mapper;

	@Override
	public Model save(Model model) {
		log.info("created model {} : " + model);
		return modelRepository.save(model);
	}

	@Override
	public ModelResponse getModelByModelId(Integer id) {

		Model model = modelRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("model id " + id + " not found."));

		ModelResponse response = new ModelResponse();
		response.setBrandId(model.getBrand().getBrandId());
		response.setModelId(model.getModelId());
		response.setName(model.getModelName());
		response.setVersion(model.getVersion());

		log.info("updated model {} : " + response);
		return response;

	}

	@Override
	public List<Model> getAllModels() {
		List<Model> list = modelRepository.findAll();
		if (list.isEmpty()) {
			throw new NotFoundException("No model is found.");
		}
		return list;
	}

}
