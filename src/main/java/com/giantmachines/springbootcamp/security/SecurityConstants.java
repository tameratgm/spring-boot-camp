package com.giantmachines.springbootcamp.security;

public interface SecurityConstants {

  String SECRET = "SecretKeyToGenJWTs";

  long EXPIRATION_TIME = 864_000_000; // 10 days

  String TOKEN_PREFIX = "Bearer ";

  String HEADER_STRING = "Authorization";

  String SIGN_UP_URL = "/users/signup";

}
