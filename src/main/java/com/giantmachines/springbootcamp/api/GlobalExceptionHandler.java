package com.giantmachines.springbootcamp.api;

import com.giantmachines.springbootcamp.api.responses.ApiError;
import com.giantmachines.springbootcamp.utils.ResponseFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

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

  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException e) {
    List<String> violations = e.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());

    ApiError error = ApiError.builder()
            .message("validation failed")
            .status(HttpStatus.BAD_REQUEST)
            .errors(violations)
            .build();

    return ResponseFactory.of(HttpStatus.BAD_REQUEST, error);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  ResponseEntity<ApiError> handleHttpClientErrorException(HttpClientErrorException e) {
    ApiError error = ApiError.builder()
            .message(e.getLocalizedMessage())
            .status(e.getStatusCode())
            .errors(List.of(e.getResponseBodyAsString()))
            .build();

    return ResponseFactory.of(e.getStatusCode(), error);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    List<String> details = ex.getBindingResult().getAllErrors().stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());

    ApiError error = ApiError.builder()
            .status(status)
            .message("method argument validation failed")
            .errors(details)
            .build();

    return ResponseFactory.of(status, error);
  }
}
