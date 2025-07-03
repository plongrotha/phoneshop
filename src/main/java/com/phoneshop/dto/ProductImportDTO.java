package com.phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate importDate;

}
