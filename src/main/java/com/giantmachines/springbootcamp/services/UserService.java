package com.giantmachines.springbootcamp.services;

import com.giantmachines.springbootcamp.models.User;
import com.giantmachines.springbootcamp.requests.CreateUserRequest;

import java.util.Optional;

public interface UserService {

  Optional<User> get(long id);

  User create(CreateUserRequest request);
}
