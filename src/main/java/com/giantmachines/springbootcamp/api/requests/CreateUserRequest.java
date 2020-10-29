package com.giantmachines.springbootcamp.api.requests;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CreateUserRequest {

  @NotEmpty
  @NotBlank
  private String firstName;

  @NotEmpty
  @NotBlank
  private String lastName;

  @NotNull
  @Size(min = 5, max = 20, message = "username must be between 5 and 20 characters long")
  private String username;

  @NotNull
  @Size(min = 5, max = 20, message = "password must be between 5 and 20 characters long")
  private String password;

}
