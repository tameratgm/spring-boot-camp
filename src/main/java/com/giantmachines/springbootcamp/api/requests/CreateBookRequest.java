package com.giantmachines.springbootcamp.api.requests;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
public class CreateBookRequest {

  @NotEmpty
  @NotBlank
  private String title;
}
