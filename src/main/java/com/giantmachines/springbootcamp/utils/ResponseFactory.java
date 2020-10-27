package com.giantmachines.springbootcamp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseFactory {

  public static <T> ResponseEntity<T> created(T object) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(object);
  }

  public static <T> ResponseEntity<T> ok(T object) {
    return ok(Optional.ofNullable(object));
  }

  public static <T> ResponseEntity<T> ok(Optional<T> object) {
    return ResponseEntity
            .of(object);
  }

  public static <T> ResponseEntity<T> of(HttpStatus status, T object) {
    return ResponseEntity
            .status(status)
            .body(object);
  }
}
