package com.springapp.spring_api;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;

public class AppUser implements UserDetails{

  @Column(name = "email", nullable = false, unique = true, length = 100)
  private String email;

  @Column(name = "username", nullable = false, unique = true, length = 100)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList(); //No Authorities
  }

  @Override
  public String getPassword() {
      return password;
  }

  @Override
  public String getUsername() {
      return username;
  }

  @Override
  public boolean isAccountNonExpired() {
      return true; // Account is not expired
  }

  @Override
  public boolean isAccountNonLocked() {
      return true; // Account is not locked
  }

  @Override
  public boolean isCredentialsNonExpired() {
      return true; // Credentials are not expired
  }

  @Override
  public boolean isEnabled() {
      return true; // Account is enabled
  }

  // Getters and setters for email and other fields
  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public void setPassword(String password) {
      this.password = password;
  }
}
