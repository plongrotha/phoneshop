package com.phoneshop.mapper;

import com.phoneshop.dto.ProductDTO;
import com.phoneshop.dto.ProductImportDTO;
import com.phoneshop.model.entity.Product;
import com.phoneshop.model.entity.ProductImportHistory;
import com.phoneshop.model.response.ProductReponse;
import com.phoneshop.service.ColorService;
import com.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

    @Mapping(target = "model.modelId", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "productImportHistoryId", ignore = true)
    @Mapping(target = "pricePerUnit", source = "productImportDTO.importPrice")
    @Mapping(target = "product", source = "product")
    ProductImportHistory toProductImportHistory(ProductImportDTO productImportDTO, Product product);

//    private Long productId;
//    private String productName;
//    private Integer availableUnit;
//    private String imageUrl;
//    private BigDecimal salePrice;
//    private String modelName;
//    private String colorName;
    @Mapping(target = "colorName", source = "color.colorName")
    @Mapping(target = "modelName", source = "model.modelName")
    ProductReponse toProductReponse(Product product);

//
//    @Mapping(target = "modelName", source = "model.modelName")
//    @Mapping(target = "colorName", source = "color.colorName")
    List<ProductReponse> toProductReponseList(List<Product> productList);

}
