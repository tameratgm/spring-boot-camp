package com.giantmachines.springbootcamp.api.requests;

import com.giantmachines.springbootcamp.enums.BookOperation;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PatchBookRequest {

  @NotNull
  private BookOperation operation;

  private long userId;

}
