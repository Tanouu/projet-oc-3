package com.tanou.projet.oc.backend.projet2.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String email);
}
