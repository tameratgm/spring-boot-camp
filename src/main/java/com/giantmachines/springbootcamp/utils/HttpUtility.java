package com.giantmachines.springbootcamp.utils;

import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class HttpUtility {

  private static final RestTemplate template = new RestTemplate();

  public static <T> Optional<T> get(String url, Class<T> clazz) {
    T response = template.getForObject(url, clazz);

    return Optional.ofNullable(response);
  }
}
