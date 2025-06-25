package com.phoneshop.mapper;

import com.phoneshop.dto.ProductDTO;
import com.phoneshop.model.entity.Product;
import com.phoneshop.service.ColorService;
import com.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

    @Mapping(target = "model.modelId", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "modelId", source = "model.modelId")
    @Mapping(target = "colorId", source = "color.colorId")
    ProductDTO toProduct(Product product);

}
