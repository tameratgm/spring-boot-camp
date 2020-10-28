package com.giantmachines.springbootcamp.api.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

  private HttpStatus status;

  private String message;

  @Builder.Default
  private List<String> errors = List.of();

}
