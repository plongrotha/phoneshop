package com.phoneshop.org.service;

import java.util.List;
import java.util.Map;

import com.phoneshop.org.model.entity.Brand;

public interface BrandService {
	Brand createBrand(Brand brand);
	Brand getBrandById(Long id);
	String deleteBrandById(Long id);
	Brand updateBrandById(Long id, Brand brand);
	// List<Brand> getAllBrands();
  // List<Brand> getAllBrands(String name);
  List<Brand> getAllBrands(Map<String, String> params);

}
