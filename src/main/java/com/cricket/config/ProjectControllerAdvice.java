package com.cricket.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ProjectControllerAdvice {
  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String unknownException(Exception ex, WebRequest req) {
    return ex.getMessage();
  }
}
