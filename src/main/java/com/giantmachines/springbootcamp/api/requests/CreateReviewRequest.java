package com.giantmachines.springbootcamp.api.requests;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
public class CreateReviewRequest {

  @Positive
  private long userId;

  @Min(1)
  @Max(5)
  private int score;

  @NotEmpty
  @NotBlank
  private String title;

  @NotEmpty
  @NotBlank
  private String text;
}
