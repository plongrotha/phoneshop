package com.phoneshop.model.response;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T>{
  
  private Boolean success;
  private String message;
  private T payload;
  private HttpStatus status;
  private LocalTime timestamp;

}
