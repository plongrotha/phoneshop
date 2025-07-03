package com.phoneshop.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImportDTO {

    @NotNull(message = "product not be null")
    @Positive
    private Long productId;

    @NotNull
    @Positive
    private Integer importUnit;

//    @NotNull
//    @Positive
//    private BigDecimal importPrice;

    private LocalDate importDate;

}
