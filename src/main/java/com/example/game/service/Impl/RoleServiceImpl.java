package com.example.game.service.Impl;

import com.example.game.entity.Role;
import com.example.game.repository.RoleRepository;
import com.example.game.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.getById(id);
    }
}