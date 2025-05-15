package com.phoneshop.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(NotFoundException.class)
	public ProblemDetail handleNotFoundException(NotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Not Found Exception");
		return problemDetail;
	}

   @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handlValidationException(MethodArgumentNotValidException e){
    ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    detail.setTitle("validation is false.");
    Map<String, String> err = new HashMap<>();
    for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
      err.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    detail.setProperty("errors", err);
    return detail;
  }

}
