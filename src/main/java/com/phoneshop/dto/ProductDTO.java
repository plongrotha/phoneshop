package com.phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @JsonProperty("model_id")
    @NotNull
    private Long modelId;

    @JsonProperty("color_id")
    @NotNull
    private Long colorId;

}
