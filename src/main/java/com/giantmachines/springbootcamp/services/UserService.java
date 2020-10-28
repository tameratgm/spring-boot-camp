package com.giantmachines.springbootcamp.services;

import com.giantmachines.springbootcamp.api.requests.CreateUserRequest;
import com.giantmachines.springbootcamp.models.User;

import java.util.Optional;

public interface UserService {

  Optional<User> get(long id);

  User create(CreateUserRequest request);
}
