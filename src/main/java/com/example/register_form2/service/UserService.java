package com.example.register_form2.service;


import com.example.register_form2.dto.UserRegistrationDto;
import com.example.register_form2.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
