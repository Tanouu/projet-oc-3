package com.tanou.projet.oc.backend.projet2.service;

import com.tanou.projet.oc.backend.projet2.dto.RegisterDto;
import com.tanou.projet.oc.backend.projet2.dto.UserDto;
import com.tanou.projet.oc.backend.projet2.entity.User;

public interface UserService {
    User registerNewUser(RegisterDto registerDto);
    User findUserByEmail(String email);
    UserDto getUserDto(User user);

}
