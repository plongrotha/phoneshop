package com.phoneshop.service;

import java.util.List;

import com.phoneshop.model.entity.Model;

public interface ModelService {

    Model save(Model model);

    Model getModelById(Long id);

    List<Model> getAllModels();

    void deleteModelByModelId(Long id);

}
