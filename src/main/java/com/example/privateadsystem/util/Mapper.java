package com.example.privateadsystem.util;

import com.example.privateadsystem.model.User;
import com.example.privateadsystem.model.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
