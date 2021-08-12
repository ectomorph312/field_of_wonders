package com.example.game.dtoService;

import com.example.game.dto.CreateUserDto;
import com.example.game.dto.UpdateUserDto;
import com.example.game.dto.UserDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserDtoService {
    UserDto create(CreateUserDto createUserDto);
    UserDto update(UpdateUserDto updateUserDto);
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto getCurrentUser(Authentication authentication);
    UserDto getByUsername(String username);
}