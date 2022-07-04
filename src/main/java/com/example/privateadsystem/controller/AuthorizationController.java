package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.User;
import com.example.privateadsystem.service.UserService;
import com.example.privateadsystem.util.Mapper;
import com.example.privateadsystem.model.dto.UserDto;
import com.example.privateadsystem.model.dto.UserNewDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    private final UserService userService;
    private final Mapper mapper;

    public AuthorizationController(UserService userService,
                                   Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<UserDto> authorization(@Valid @RequestBody UserNewDto userNewDto) {
        logger.info("Authorization user {}", userNewDto);
        User user = userService.findByUserLoginAndPassword(userNewDto.getUsername(), userNewDto.getPassword());
        UserDto userDto = mapper.convertUserToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
