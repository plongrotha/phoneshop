package com.phoneshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModelDTO {
	
	@NotNull(message = "brand id must be not null")
	private Long brandId;
	
	@NotNull(message = "model name can not be null")
	private String name;
	
	@Min(value = 1)
	private Double version;
}
