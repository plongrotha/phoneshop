package com.phoneshop.service.impl;

import com.phoneshop.exception.NotFoundException;
import com.phoneshop.mapper.ModelMapper;
import com.phoneshop.model.entity.Model;
import com.phoneshop.repository.ModelRepository;
import com.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    public Model save(Model model) {
        log.info("created model {} : {}", model);
        return modelRepository.save(model);
    }

    @Cacheable(value = "myCache", key = "#id")
    @Override
    public Model getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("model id " + id + " not found."));
    }

    @Override
    public List<Model> getAllModels() {
        List<Model> list = modelRepository.findAll();
        modelMapper.toListModelDTO(list);
        if (list.isEmpty()) {
            throw new NotFoundException("No model is found.");
        }
        return list;
    }

    @Cacheable(value = "myCache", key = "#id")
    @Override
    public List<Model> getByBrandId(Long brandId) {
        List<Model> list = modelRepository.findAllByBrand_BrandId(brandId);
        if (list.isEmpty()) {
            throw new NotFoundException("No model is found.");
        }
        return list;
    }
}
