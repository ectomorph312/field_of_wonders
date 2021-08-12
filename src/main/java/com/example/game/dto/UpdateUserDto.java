package com.example.game.dto;

import lombok.Data;

@Data
public class UpdateUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
}
