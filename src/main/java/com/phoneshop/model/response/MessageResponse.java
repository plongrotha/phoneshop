package com.phoneshop.model.response;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse<T> {
	private HttpStatus status;
	private String message;
}
