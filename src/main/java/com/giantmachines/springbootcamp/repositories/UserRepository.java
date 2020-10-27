package com.giantmachines.springbootcamp.repositories;

import com.giantmachines.springbootcamp.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  
}
