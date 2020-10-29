package com.giantmachines.springbootcamp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.giantmachines.springbootcamp.security.SecurityConstants.HEADER_STRING;
import static com.giantmachines.springbootcamp.security.SecurityConstants.SECRET;
import static com.giantmachines.springbootcamp.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  public JWTAuthorizationFilter(AuthenticationManager authManager) {
    super(authManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
                                  HttpServletResponse res,
                                  FilterChain chain) throws IOException, ServletException {
    getAuthHeader(req)
            .filter(header -> header.startsWith(TOKEN_PREFIX))
            .flatMap(bearer -> getAuthenticationToken(req))
            .ifPresent(authenticationToken -> SecurityContextHolder.getContext()
                    .setAuthentication(authenticationToken));

    chain.doFilter(req, res);
  }

  private Optional<String> getAuthHeader(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(HEADER_STRING));
  }

  private Optional<UsernamePasswordAuthenticationToken> getAuthenticationToken(HttpServletRequest request) {
    return getAuthHeader(request)
            .flatMap(this::verifyToken)
            .map(user -> new UsernamePasswordAuthenticationToken(user, null, List.of()));
  }

  private Optional<String> verifyToken(String token) {
    String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
            .build()
            .verify(token.replace(TOKEN_PREFIX, ""))
            .getSubject();

    return Optional.ofNullable(user);
  }
}
