package com.giantmachines.springbootcamp.api;

import com.giantmachines.springbootcamp.api.responses.ApiError;
import com.giantmachines.springbootcamp.utils.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResponseStatusException.class)
  ResponseEntity<ApiError> handleResponseStatusException(ResponseStatusException e) {
    HttpStatus status = e.getStatus();

    ApiError error = ApiError.builder()
            .status(status)
            .message(e.getLocalizedMessage())
            .build();

    return ResponseFactory.of(status, error);
  }

}
