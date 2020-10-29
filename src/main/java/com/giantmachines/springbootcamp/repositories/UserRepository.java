package com.giantmachines.springbootcamp.repositories;

import com.giantmachines.springbootcamp.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByUsername(String userName);

}
