package com.phoneshop.mapper;

import com.phoneshop.dto.BrandDTO;
import com.phoneshop.model.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BrandMapper {

	// transform from Brand -> BrandDTO
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

	Brand toBrand(BrandDTO brandDTO);

	BrandDTO toBrandDTO(Brand brand);
}
