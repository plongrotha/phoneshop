package com.phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelDTO {
	
	@NotNull(message = "brand id must be not null")
	@Schema(example = "1", description = "ID of the brand this model belongs to")
	private Long brandId;

	@NotNull(message = "model name can not be null")
	@Schema(example = "Iphone Xs", description = "Name of the product model")
	private String modelName;

	@Min(value = 1)
	@Schema(example = "1.8", description = "Version number of the model; must be 1.0 or higher")
	private Double version;

}
