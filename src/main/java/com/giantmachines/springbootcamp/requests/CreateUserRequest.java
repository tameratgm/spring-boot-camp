package com.giantmachines.springbootcamp.requests;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
public class CreateUserRequest {

  @NotEmpty
  @NotBlank
  private String firstName;

  @NotEmpty
  @NotBlank
  private String lastName;
  
}
