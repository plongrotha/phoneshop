package com.phoneshop.org.service.mapper;

import com.phoneshop.org.dto.BrandDTO;
import com.phoneshop.org.model.entity.Brand;

public class Mapper {
  public static Brand toBrand(BrandDTO brandDTO) {
    Brand brand = new Brand();  
    // brand.setBrandId(brandDTO.getId());
    brand.setBrandName(brandDTO.getName());
    return brand;
  }

  public static BrandDTO toBrandDTO(Brand brand){
     BrandDTO brandDTO = new BrandDTO();
     brandDTO.setName(brand.getBrandName());
     return brandDTO;
  }

}
