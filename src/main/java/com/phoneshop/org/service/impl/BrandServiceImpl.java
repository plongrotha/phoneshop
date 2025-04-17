package com.phoneshop.org.service.impl;

import java.util.List;
import java.util.Map;

import org.springdoc.core.converters.models.Sort;
import org.springframework.stereotype.Service;

import com.phoneshop.org.exception.NotFoundException;
import com.phoneshop.org.model.entity.Brand;
import com.phoneshop.org.repository.BrandRepository;
import com.phoneshop.org.service.BrandService;

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

	// @Override
	// public List<Brand> getAllBrands() {
	// 	List<Brand> brands = brandRepository.findAll();
	// 	if (brands.isEmpty()) {
	// 		throw new NotFoundException("No Brands found");
	// 	}
	// 	return brands;
	// }

  
  // @Override
  // public List<Brand> getAllBrands(String name) {
  //   List<Brand> brands = brandRepository.findByBrandNameContaining(name);
  //   if (brands.isEmpty()) {
  //     throw new NotFoundException("No Brands found with name " + name);
  //   }
  //   return brands;
  // } 

	@Override
	public Brand getBrandById(Long id) {
		log.info("fetching brand by id => " + id);
		return brandRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(" Brand with id " + id + " is not found"));
	}

	@Override
	public Brand updateBrandById(Long id, Brand brand) {
		// Brand existingBrand = brandRepository.findById(id)
		// 		.orElseThrow(() -> new NotFoundException(" Brand with id " + id + " is not found"));
    Brand existingBrand = getBrandById(id);
		existingBrand.setBrandName(brand.getBrandName());
		return brandRepository.save(existingBrand);
	}

	@Override
	public String deleteBrandById(Long id) {
//		Brand brand = brandRepository.findById(id)
//				.orElseThrow(() -> new NotFoundException(" Brand with id " + id + " is not found"));
    log.info("deleting brand with id : -> " + id );
		Brand brand = getBrandById(id);
		brandRepository.delete(brand);

		return "Brand with id " + id + " has been deleted successfully.";
	}

  @Override
  public List<Brand> getAllBrands(Map<String, String> params) {
    throw new UnsupportedOperationException("Unimplemented method 'getAllBrands'");
  }


  

}
