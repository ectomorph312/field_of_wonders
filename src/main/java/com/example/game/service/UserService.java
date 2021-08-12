package com.example.game.service;

import com.example.game.entity.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User save(User user);
    List<User> getAll();
    User getByUsername(String username);
}