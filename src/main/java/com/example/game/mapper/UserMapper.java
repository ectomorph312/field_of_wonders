package com.example.game.mapper;

import com.example.game.dto.UserDto;
import com.example.game.entity.User;

import java.util.List;

public interface UserMapper {
    UserDto toUserDto(User user);
    List<UserDto> toUserDtos(List<User> users);
}