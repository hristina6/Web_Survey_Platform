package com.example.survey_app.service;

import com.example.survey_app.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    void save(User user);

    String getCurrentUsername();
}