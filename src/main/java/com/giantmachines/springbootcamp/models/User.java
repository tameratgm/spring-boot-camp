package com.giantmachines.springbootcamp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
// NOTE: we use the table name "users" here to avoid conflicting with the reserved JPA keyword "user"
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User {

  @Id
  @GeneratedValue
  private long id;

  private String firstName;

  private String lastName;

  @JsonIgnore
  @OneToMany(mappedBy = "owner")
  @Builder.Default
  private List<Book> books = List.of();

}
