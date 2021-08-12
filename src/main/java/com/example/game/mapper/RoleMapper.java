package com.example.game.mapper;

import com.example.game.dto.RoleDto;
import com.example.game.entity.Role;

public interface RoleMapper {
    RoleDto toRoleDto(Role role);
}