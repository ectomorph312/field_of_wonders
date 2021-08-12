package com.example.game.dtoService.Impl;

import com.example.game.dto.CreateUserDto;
import com.example.game.dto.UpdateUserDto;
import com.example.game.dto.UserDto;
import com.example.game.dtoService.UserDtoService;
import com.example.game.entity.User;
import com.example.game.mapper.UserMapper;
import com.example.game.service.RoleService;
import com.example.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDtoServiceImpl implements UserDtoService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Autowired
    public UserDtoServiceImpl(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService,
                              UserMapper userMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return getByUsername(userDetails.getUsername());
    }

    @Override
    public UserDto getByUsername(String username) {
        return userMapper.toUserDto(userService.getByUsername(username));
    }

    @Override
    public UserDto create(CreateUserDto createUserDto) {
        User user = new User();

        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail().toLowerCase());
        user.setUsername(createUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user.setRole(roleService.getById(2L));
        user.setActive(true);

        return userMapper.toUserDto(userService.save(user));
    }

    @Override
    public UserDto update(UpdateUserDto updateUserDto) {
        User updUser = userService.getById(updateUserDto.getId());

        if (updateUserDto.getFirstName() != null) {
            updUser.setFirstName(updateUserDto.getFirstName());
        }

        if (updateUserDto.getLastName() != null) {
            updUser.setLastName(updateUserDto.getLastName());
        }

        if (updateUserDto.getEmail() != null) {
            updUser.setEmail(updateUserDto.getEmail());
        }

        if (updateUserDto.getUsername() != null) {
            updUser.setUsername(updateUserDto.getUsername());
        }

        return userMapper.toUserDto(userService.save(updUser));
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toUserDto(userService.getById(id));
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.toUserDtos(userService.getAll());
    }
}
