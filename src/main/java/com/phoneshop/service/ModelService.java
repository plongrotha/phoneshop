package com.phoneshop.service;

import com.phoneshop.dto.ModelDTO;
import com.phoneshop.model.response.ModelResponse;

public interface ModelService {
	ModelResponse save(ModelDTO model);
}
