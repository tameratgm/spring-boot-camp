package com.giantmachines.springbootcamp.controllers;

import com.giantmachines.springbootcamp.api.requests.CreateUserRequest;
import com.giantmachines.springbootcamp.models.User;
import com.giantmachines.springbootcamp.services.UserService;
import com.giantmachines.springbootcamp.utils.ResponseFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<User> signup(@Valid @RequestBody CreateUserRequest request) {

    User user = userService.create(request);

    return ResponseFactory.created(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable long id) {

    Optional<User> user = userService.get(id);

    return ResponseFactory.ok(user);
  }

}
