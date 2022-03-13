package com.prueba.trabe.security;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MyUserDetailsService implements UserDetailsService {
  private final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

  

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
   
    if (login == null) {
      throw new UsernameNotFoundException("User " + login + " not found");
    }
    logger.info("Loaded user {} with authority {}", login, login);
    GrantedAuthority authority = new SimpleGrantedAuthority(login);
    return new org.springframework.security.core.userdetails.User(login, login,
        Collections.singleton(authority));
  }
}
