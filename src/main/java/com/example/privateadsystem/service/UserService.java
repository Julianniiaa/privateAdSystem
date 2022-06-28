package com.example.privateadsystem.service;

import com.example.privateadsystem.model.User;
import com.example.privateadsystem.web.dto.UserDto;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    User saveUser(UserDto userDto) throws ParseException;
    User getUserById(long id);
    User updateUser(UserDto userDto);
    List<User> allUsers();
    User getUserByUsername(String username);
    void deleteUser(long id);
}
