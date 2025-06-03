package com.phoneshop.service.impl;

import com.phoneshop.exception.BrandAlreadyExistsException;
import com.phoneshop.exception.NotFoundException;
import com.phoneshop.model.entity.Brand;
import com.phoneshop.repository.BrandRepository;
import com.phoneshop.service.BrandService;
import com.phoneshop.specification.BrandFilter;
import com.phoneshop.specification.BrandSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
//
//	private final Function<Long, Brand> findBrandById = (id) -> brandRepository.findById(id).orElseThrow((() -> new NotFoundException("Brand with id " + id + " not found")));
//	private final Consumer<Brand> insertBrand = brandRepository::save;


    // i deleted return type Brand
    @Override
    public void createBrand(Brand brand) {
        Optional<Brand> existBrand = brandRepository.findBrandByBrandName(brand.getBrandName());

        if (existBrand.isPresent()) {
            throw new BrandAlreadyExistsException("Brand '" + brand.getBrandName() + "' already exists");
        }
            brandRepository.save(brand);

    }


    @Override
    public List<Brand> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        log.info("get all brands : {}", brands.size());
        return Optional.of(brands).filter(list -> !list.isEmpty()).orElseThrow(() -> new NotFoundException("No Brands found."));
    }

    @Override
    public List<Brand> getAllBrandSpecification(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();

        if (params.containsKey("name")){
            String name = params.get("name");
            brandFilter.setBrandName(name);
        }

        if (params.containsKey("id")){
            String id = params.get("id");
            brandFilter.setBrandId(Integer.parseInt(id));
        }

        BrandSpecification brandSpecification = new BrandSpecification(brandFilter);
        return brandRepository.findAll(brandSpecification);
    }

    @Override
    public Brand getBrandById(Long id) {
        log.info("fetching brand by id => {}", id);
        return brandRepository.findById(id).orElseThrow(() -> new NotFoundException("No Brand found."));
//		return brandRepository.findById(id).orElseThrow(() -> new NotFoundException(" Brand with id " + id + " is not found"));
    }

    @Override
    public Brand updateBrandById(Long id, Brand brand) {
        Brand existingBrand = getBrandById(id);
        existingBrand.setBrandName(brand.getBrandName());
        existingBrand.setVersion(brand.getVersion());
        return brandRepository.save(existingBrand);
    }

    @Override
    public void deleteBrandById(Long id) {
        Brand brand = getBrandById(id);
        log.info("deleting brand with id : -> {}", id);
        brandRepository.delete(brand);
    }
}
