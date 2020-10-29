package com.giantmachines.springbootcamp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Book {

  @Id
  @GeneratedValue
  private long id;

  private String title;
  
  @ManyToOne(cascade = CascadeType.ALL)
  private User owner;

  @OneToMany(mappedBy = "book")
  @Builder.Default
  private List<Review> reviews = List.of();

  @JsonIgnore
  public boolean isCheckedOut() {
    return owner != null;
  }

  @JsonProperty
  public double score() {
    return reviews.stream()
                   .mapToDouble(Review::getScore)
                   .reduce(0f, Double::sum) / reviews.size();
  }
}
