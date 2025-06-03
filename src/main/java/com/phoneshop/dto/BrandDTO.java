package com.phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandDTO {

    // at this we convert Brand to BrandDTO what we give user can input.
    // so we not need user input id and the id is increase auto in database

    @NotNull(message = "Brand ID cannot be null")
    @Size(min = 1, message = "Brand ID must be at least 1 character long")
    private String brandName;


    @Min(value = 1)
    @NotNull(message = "Version cannot be null")
    private Integer vs;

}
