package com.giantmachines.springbootcamp.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.giantmachines.springbootcamp.security.SecurityConstants.EXPIRATION_TIME;
import static com.giantmachines.springbootcamp.security.SecurityConstants.HEADER_STRING;
import static com.giantmachines.springbootcamp.security.SecurityConstants.SECRET;
import static com.giantmachines.springbootcamp.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
                                              HttpServletResponse res) throws AuthenticationException {
    try {
      com.giantmachines.springbootcamp.models.User creds = readUserCredentials(req);

      UsernamePasswordAuthenticationToken token = createToken(creds);

      return authenticationManager.authenticate(token);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private com.giantmachines.springbootcamp.models.User readUserCredentials(HttpServletRequest req) throws IOException {
    ObjectMapper mapper = new ObjectMapper().disable(MapperFeature.USE_ANNOTATIONS);

    return mapper.readValue(req.getInputStream(), com.giantmachines.springbootcamp.models.User.class);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
                                          HttpServletResponse res,
                                          FilterChain chain,
                                          Authentication auth) {

    User principal = getPrincipal(auth);
    String token = JWT.create()
            .withSubject(principal.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));

    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }

  private User getPrincipal(Authentication auth) {
    return (User) auth.getPrincipal();
  }

  private UsernamePasswordAuthenticationToken createToken(com.giantmachines.springbootcamp.models.User creds) {
    return new UsernamePasswordAuthenticationToken(
            creds.getUsername(),
            creds.getPassword(),
            new ArrayList<>());
  }

}