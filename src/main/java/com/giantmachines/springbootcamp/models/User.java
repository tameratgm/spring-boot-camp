package com.giantmachines.springbootcamp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
