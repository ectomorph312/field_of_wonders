package com.example.game.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;
}