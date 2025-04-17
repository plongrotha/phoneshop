package com.phoneshop.org.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.phoneshop.org.dto.BrandDTO;
import com.phoneshop.org.model.entity.Brand;

@Mapper
public interface BrandMapper {

	// tranform from Brand -> BrandDTO

	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

	@Mapping(target = "version", source = "vs")
	Brand toBrand(BrandDTO brandDTO);

	BrandDTO toBrandDTO(Brand brand);
}
