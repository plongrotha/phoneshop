package com.phoneshop.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.phoneshop.dto.ModelDTO;
import com.phoneshop.model.entity.Model;
import com.phoneshop.service.BrandService;

@Mapper(componentModel = "spring", uses = { BrandService.class })
public interface ModelMapper {

	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

	@Mapping(target = "brand", source = "brandId")
	Model toModel(ModelDTO dto);
	
	@Mapping(target = "brandId", source = "brand.brandId")
	ModelDTO toModelDTO(Model model);
	

}
