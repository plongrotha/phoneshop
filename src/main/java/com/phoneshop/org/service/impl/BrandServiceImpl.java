package com.phoneshop.org.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.phoneshop.org.exception.NotFoundException;
import com.phoneshop.org.model.entity.Brand;
import com.phoneshop.org.repository.BrandRepository;
import com.phoneshop.org.service.BrandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand createbrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
      List<Brand> brands = brandRepository.findAll();
      if(brands.isEmpty()){
        throw new NotFoundException("No Brands not found");
      }
        return brands;
    }

    @Override
    public Brand getBrandById(Long id) {
      return brandRepository.findById(id).orElseThrow(() -> new NotFoundException(" Brand with id " + id + " is not found" ));
    }

    @Override
    public void deleteBrandById(Long id) {
     Brand brand = brandRepository.findById(id).orElseThrow(() -> new NotFoundException(" Brand with id " + id + " is not found" ));
     brandRepository.delete(brand);
    }

    @Override
    public Brand updateBrandById(Long id, Brand brand) {
      Brand existingBrand = brandRepository.findById(id).orElseThrow(() -> new NotFoundException(" Brand with id " + id + " is not found" ));
      existingBrand.setBrandName(brand.getBrandName());
      return brandRepository.save(existingBrand);
      }

}
