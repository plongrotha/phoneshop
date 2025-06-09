package com.phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandDTO {

	// at this we convert Brand to BrandDTO what we give user can input.
	// so we not need user input id and the id is increase auto in database
	@Schema(example = "Apple", description = "Name of the brand")
	@NotNull(message = "Brand Name cannot be null")
	private String brandName;

}
