package com.giantmachines.springbootcamp.api.responses.integrations.nyt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// There are many more fields in the response from NY Times
// but we choose to ignore most of them except the ones specified below
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewYorkTimesBestSellerResponse implements Serializable {

  @Getter
  private static class Result {
    private List<NewYorkTimesBook> books = List.of();
  }

  private Result results;

  public List<NewYorkTimesBook> getBooks() {
    return Optional.ofNullable(results)
            .map(Result::getBooks)
            .orElse(List.of());
  }

}
