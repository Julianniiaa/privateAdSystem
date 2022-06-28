package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.User;
import com.example.privateadsystem.service.UserService;
import com.example.privateadsystem.web.dto.PostDto;
import com.example.privateadsystem.web.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User createUser(@RequestBody UserDto userDto) throws ParseException {
        return userService.saveUser(userDto);
    }

    @DeleteMapping("/{id}")
    public String deleteMapping(@PathVariable(value = "id") long id) {
        this.userService.deleteUser(id);
        return "redirect:/users";
    }
}
