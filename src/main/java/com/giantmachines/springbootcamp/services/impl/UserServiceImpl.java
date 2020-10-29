package com.giantmachines.springbootcamp.services.impl;

import com.giantmachines.springbootcamp.api.requests.CreateUserRequest;
import com.giantmachines.springbootcamp.models.User;
import com.giantmachines.springbootcamp.repositories.UserRepository;
import com.giantmachines.springbootcamp.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserServiceImpl(UserRepository userRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
            .username(request.getUsername())
            .password(encrypt(request.getPassword()))
            .build();

    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return userRepository.findByUsername(userName)
            .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List
                    .of()))
            .orElseThrow(() -> new UsernameNotFoundException(userName));
  }

  private String encrypt(String password) {
    return bCryptPasswordEncoder.encode(password);
  }
}
