package com.tanou.projet.oc.backend.projet2.service;


import com.tanou.projet.oc.backend.projet2.dto.UserDto;
import com.tanou.projet.oc.backend.projet2.entity.User;


public interface UserService {
    User registerNewUser(UserDto userDto);
    User findUserByEmail(String email);

}
