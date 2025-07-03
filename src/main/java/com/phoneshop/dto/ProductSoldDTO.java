package com.phoneshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSoldDTO {
    private String productId;
    private int numberOfUnit;
    private BigDecimal unitPrice;

}
