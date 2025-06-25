package com.phoneshop.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T>{
  
  private Boolean success;
  private String message;
  private T payload;
  private int status;
  private LocalDateTime timestamp;

}
