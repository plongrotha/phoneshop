package com.phoneshop.org.service;

import java.util.List;

import com.phoneshop.org.model.entity.Brand;

public interface BrandService {
    Brand createbrand(Brand brand);
    List<Brand> getAllBrands();
    Brand getBrandById(Long id);
    void deleteBrandById(Long id);
    Brand updateBrand(Long id, Brand brand);

    


}
