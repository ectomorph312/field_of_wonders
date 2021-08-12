package com.example.game.mapper.Impl;

import com.example.game.dto.RoleDto;
import com.example.game.entity.Role;
import com.example.game.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleMapperImpl implements RoleMapper {
    @Override
    public RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setName(role.getName());

        return roleDto;
    }
}