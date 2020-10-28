package com.giantmachines.springbootcamp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Review {

  @Id
  @GeneratedValue
  private long id;

  @JsonManagedReference
  @ManyToOne(cascade = CascadeType.ALL)
  private User writer;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  private Book book;

  private String title;

  private String text;

  private int score;
}
