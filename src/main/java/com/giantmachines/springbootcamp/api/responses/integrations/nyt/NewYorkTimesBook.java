package com.giantmachines.springbootcamp.api.responses.integrations.nyt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

// There are many more fields in the response from NY Times
// but we choose to ignore most of them except the ones specified below
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewYorkTimesBook implements Serializable {

  private String title;

  private String author;

  private String description;
}
