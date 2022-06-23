package com.example.privateadsystem;

import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.UserService;
import com.example.privateadsystem.web.dto.UserDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.ParseException;

@NoArgsConstructor
public class Tests {

    private UserService userService;
    public Tests(UserService userService) {
        super();
        this.userService = userService;
    }

    public void createUser() throws ParseException {
        UserDto userDto = UserDto.builder().username("julia")
                .password("12345")
                .firstname("Julia")
                .lastname("Kulichik")
                .dateOfBirth("26.01.2002")
                .build();
        userService.saveUser(userDto);
    }
}
