package com.tanou.projet.oc.backend.projet2.controller;

import com.tanou.projet.oc.backend.projet2.dto.UserDto;
import com.tanou.projet.oc.backend.projet2.service.UserDetailsService;
import com.tanou.projet.oc.backend.projet2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserDetails {

  private final UserDetailsService userService;

  @Autowired
  public UserDetails(UserDetailsService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public UserDto getUserDetails(@PathVariable Integer id) {
    return userService.getUserById(id);
  }
}
