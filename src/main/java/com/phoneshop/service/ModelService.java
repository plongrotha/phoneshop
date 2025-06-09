package com.phoneshop.service;

import java.util.List;

import com.phoneshop.model.entity.Model;
import com.phoneshop.model.response.ModelResponse;

public interface ModelService {

	Model save(Model model);

	ModelResponse getModelByModelId(Long id);
	
	List<Model> getAllModels();

}
