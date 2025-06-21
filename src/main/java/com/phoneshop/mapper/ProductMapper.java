package com.phoneshop.mapper;

import com.phoneshop.dto.ProductDTO;
import com.phoneshop.model.entity.Product;
import com.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ModelService.class})
public interface ProductMapper {

    @Mapping(target = "model.modelId", source = "modelId")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "modelId", source = "model.modelId")
    ProductDTO toProduct(Product product);

}
