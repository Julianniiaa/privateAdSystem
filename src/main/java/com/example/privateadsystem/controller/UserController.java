package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.User;
import com.example.privateadsystem.service.UserService;
import com.example.privateadsystem.web.dto.PostDto;
import com.example.privateadsystem.web.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping()
    public String createUser(@RequestBody UserDto userDto) throws ParseException {
        userService.saveUser(userDto);
        return "redirect:";

    }

    @DeleteMapping("/{id}")
    public String deleteMapping(@PathVariable(value = "id") long id) {
        this.userService.deleteUser(id);
        return "redirect:";
    }
}
