package com.giantmachines.springbootcamp.services.impl;

import com.giantmachines.springbootcamp.models.User;
import com.giantmachines.springbootcamp.repositories.UserRepository;
import com.giantmachines.springbootcamp.requests.CreateUserRequest;
import com.giantmachines.springbootcamp.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> get(long id) {
    return userRepository.findById(id);
  }

  @Override
  public User create(CreateUserRequest request) {
    User user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .build();

    return userRepository.save(user);
  }
}
