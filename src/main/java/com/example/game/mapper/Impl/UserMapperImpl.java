package com.example.game.mapper.Impl;

import com.example.game.dto.UserDto;
import com.example.game.entity.User;
import com.example.game.mapper.RoleMapper;
import com.example.game.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapperImpl implements UserMapper {
    private final RoleMapper roleMapper;

    @Autowired
    public UserMapperImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());

        if (user.getRole() != null) {
            userDto.setRoleDto(roleMapper.toRoleDto(user.getRole()));
        }

        return userDto;
    }

    @Override
    public List<UserDto> toUserDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(user -> {
            UserDto userDto = this.toUserDto(user);

            userDtos.add(userDto);
        });

        return userDtos;
    }
}