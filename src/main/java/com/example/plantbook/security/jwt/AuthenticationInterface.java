package com.example.plantbook.security.jwt;

import com.example.plantbook.entity.User;

public interface AuthenticationInterface {
    String login(String username, String password);
    User findByToken(String token);
    void logout(User user);
}
