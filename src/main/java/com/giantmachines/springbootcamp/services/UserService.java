package com.giantmachines.springbootcamp.services;

import com.giantmachines.springbootcamp.api.requests.CreateUserRequest;
import com.giantmachines.springbootcamp.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

  Optional<User> get(long id);

  User create(CreateUserRequest request);
}
