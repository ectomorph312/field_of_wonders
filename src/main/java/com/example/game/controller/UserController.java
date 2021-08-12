package com.example.game.controller;

import com.example.game.dto.CreateUserDto;
import com.example.game.dto.UpdateUserDto;
import com.example.game.dto.UserDto;
import com.example.game.dtoService.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDtoService userDtoService;

    @Autowired
    public UserController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        CreateUserDto createUserDto = new CreateUserDto();
        model.addAttribute("user", createUserDto);

        return "users/create";
    }

    @PostMapping
    public String create(@ModelAttribute CreateUserDto createUserDto) {
        userDtoService.create(createUserDto);

        return "redirect:/login";
    }

    @PutMapping
    public UserDto update(@RequestBody UpdateUserDto updateUserDto) {
        return userDtoService.update(updateUserDto);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        return userDtoService.getById(id);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userDtoService.getAll();
    }
}
