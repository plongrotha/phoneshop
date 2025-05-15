package com.phoneshop.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.phoneshop.exception.NotFoundException;
import com.phoneshop.model.entity.Brand;
import com.phoneshop.repository.BrandRepository;
import com.phoneshop.service.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

	private final BrandRepository brandRepository;

	@Override
	public Brand createBrand(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public List<Brand> getAllBrands() {
		List<Brand> brands = brandRepository.findAll();
		return Optional.of(brands).filter(list -> !list.isEmpty()).orElseThrow(() -> new NotFoundException("No Brands found."));
	}

	@Override
	public Brand getBrandById(Long id) {
        log.info("fetching brand by id => {}", id);
		return brandRepository.findById(id).orElseThrow(() -> new NotFoundException(" Brand with id " + id + " is not found"));
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

  @Override
  public List<Brand> getAllBrands(Map<String, String> params) {
    throw new UnsupportedOperationException("Filtering with parameters is not yet implemented.");
  }




   @Override
   public List<Brand> getAllBrands(String name) {
     List<Brand> brands = brandRepository.findByBrandNameContaining(name);
     if (brands.isEmpty()) {
       throw new NotFoundException("No Brands found with name " +  name);
     }
     return brands;
   }

}
