package com.phoneshop.service;

import java.util.List;
import java.util.Map;

import com.phoneshop.model.entity.Brand;

public interface BrandService {
    void createBrand(Brand brand);

    Brand getBrandById(Long id);

    void deleteBrandById(Long id);

    Brand updateBrandById(Long id, Brand brand);

    List<Brand> getAllBrands();

    List<Brand> getAllBrandByName(String name);

    List<Brand> getAllBrandSpecification(Map<String, String> params);




}
