package com.giantmachines.springbootcamp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private User owner;

  @JsonIgnore
  public boolean isCheckedOut() {
    return owner != null;
  }
}
