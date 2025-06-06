package com.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.phoneshop.dto.BrandDTO;
import com.phoneshop.model.entity.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {

	// transform from Brand -> BrandDTO
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

//	@Mapping(source = "version", target = "version")
	Brand toBrand(BrandDTO brandDTO);

//	@Mapping(source = "version", target = "version")
	BrandDTO toBrandDTO(Brand brand);
}
