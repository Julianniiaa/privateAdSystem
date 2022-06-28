package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.model.Role;
import com.example.privateadsystem.model.User;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.UserService;
import com.example.privateadsystem.web.dto.UserDto;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserDto userDto) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateOfBirth = format.parse(userDto.getDateOfBirth());
        User user = User.builder().username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .dateOfBirth(dateOfBirth)
                .roles(List.of(new Role("USER")))
                .build();
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> optional = userRepository.findById(id);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" User not found for id :: " + id);
        }
        return user;
    }

    @Override
    public User updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
//        if (user == null)
//        {
//            throw new UsernameNotFoundException("User not found for username: " + username);
//        }
        return user;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
