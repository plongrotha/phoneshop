package com.phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelDTO {
	
	@NotNull(message = "brand id must be not null")
	private Integer brandId;
	
	@NotNull(message = "model name can not be null")
	private String modelName;
	
	@Min(value = 1)
	private Double version;
}
