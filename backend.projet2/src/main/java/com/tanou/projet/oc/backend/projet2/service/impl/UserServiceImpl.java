package com.tanou.projet.oc.backend.projet2.service.impl;


import com.tanou.projet.oc.backend.projet2.dto.RegisterDto;
import com.tanou.projet.oc.backend.projet2.dto.UserDto;
import com.tanou.projet.oc.backend.projet2.entity.User;
import com.tanou.projet.oc.backend.projet2.repository.UserRepository;
import com.tanou.projet.oc.backend.projet2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User registerNewUser(RegisterDto registerDto) {
      User newUser = new User();
      newUser.setEmail(registerDto.getEmail());
      newUser.setName(registerDto.getName());
      newUser.setPassword(registerDto.getPassword());
      newUser.setCreatedAt(LocalDateTime.now());
      newUser.setUpdatedAt(LocalDateTime.now());
      return userRepository.save(newUser);
    }

    @Override
    public User findUserByEmail(String email) {
      return userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public User findUserById(Integer id) {
      return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

  @Override
  public UserDto getUserDto(User user) {
    return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
  }

}
