package com.phoneshop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BrandDTO {
	// private Long brandId;

  @NotNull(message = "Brand ID cannot be null")
  @Size(min = 1, message = "Brand ID must be at least 1 character long")
	private String brandName;

  @NotNull(message = "Version cannot be null")
	private Integer vs;

}
