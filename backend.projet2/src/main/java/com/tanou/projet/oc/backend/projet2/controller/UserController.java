package com.tanou.projet.oc.backend.projet2.controller;


import com.tanou.projet.oc.backend.projet2.dto.LoginDto;
import com.tanou.projet.oc.backend.projet2.dto.RegisterDto;
import com.tanou.projet.oc.backend.projet2.dto.UserDto;
import com.tanou.projet.oc.backend.projet2.entity.User;
import com.tanou.projet.oc.backend.projet2.security.JwtGenerator;
import com.tanou.projet.oc.backend.projet2.service.UserService;
import com.tanou.projet.oc.backend.projet2.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
  public ResponseEntity<Map<String, String>> registerNewUser(@RequestBody RegisterDto registerDto) {
    System.out.println("Registering a new user");

    registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    User newUser = userService.registerNewUser(registerDto);

    UsernamePasswordAuthenticationToken authentication =
      new UsernamePasswordAuthenticationToken(newUser.getEmail(), null, new ArrayList<>());

    final String jwt = jwtGenerator.generateToken(authentication);

    return ResponseEntity.ok(Map.of("token", jwt));
  }


  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginDto loginData) {
    User user = userService.findUserByEmail(loginData.getEmail());
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

  @GetMapping("/me")
  public ResponseEntity<UserDto> getUserProfile(Authentication authentication) {
    if (authentication == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    String email = authentication.getName();

    UserDto userDto = userService.getUserDto(userService.findUserByEmail(email));
    return ResponseEntity.ok(userDto);
  }
}

