package com.phoneshop.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReponse {

    private Long productId;
    private String productName;
    private Integer availableUnit;
    private String imageUrl;
    private BigDecimal salePrice;
    private String modelName;
    private String colorName;

}
