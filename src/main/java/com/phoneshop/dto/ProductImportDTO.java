package com.phoneshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImportDTO {

    private Long productId;
    private Integer importUnit;
    private BigDecimal importPrice;
    private LocalDate importDate;

}
