package com.tanou.projet.oc.backend.projet2.controller;


import com.tanou.projet.oc.backend.projet2.dto.LoginDto;
import com.tanou.projet.oc.backend.projet2.dto.UserDto;
import com.tanou.projet.oc.backend.projet2.entity.User;
import com.tanou.projet.oc.backend.projet2.security.JwtGenerator;
import com.tanou.projet.oc.backend.projet2.service.UserService;
import com.tanou.projet.oc.backend.projet2.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsServiceImpl userDetailsService;
  private final JwtGenerator jwtGenerator;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService, JwtGenerator jwtUtil) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
    this.jwtGenerator = jwtUtil;
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerNewUser(@RequestBody UserDto userDto) {
    System.out.println("Register a new user"); // Ajoute cette ligne pour vérifier
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    userService.registerNewUser(userDto);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/email")
  public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginDto loginData) {
    User user = userService.findUserByEmail(loginData.getLogin());
    if (user == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not found"));
    } else if (!passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid username/password"));
    }
    UsernamePasswordAuthenticationToken authentication =
      new UsernamePasswordAuthenticationToken(user.getEmail(), null, new ArrayList<>());

    final String jwt = jwtGenerator.generateToken(authentication);

    return ResponseEntity.ok(Map.of("token", jwt));
  }
}

