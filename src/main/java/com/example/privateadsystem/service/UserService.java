package com.example.privateadsystem.service;

import com.example.privateadsystem.model.User;
import com.example.privateadsystem.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;
import java.util.List;

public interface UserService extends UserDetailsService {
    User saveUser(UserDto userDto) throws ParseException;
    User getUserById(long id);
    User updateUser(long id, UserDto userDto) throws ParseException;
    List<User> allUsers();
    void deleteUser(long id);
    User findByUserLoginAndPassword(String username, String password);
    String getUsername(long id);
}
