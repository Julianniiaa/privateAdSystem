package com.example.privateadsystem;

import com.example.privateadsystem.model.User;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.UserService;
import com.example.privateadsystem.service.impl.UserServiceImpl;
import com.example.privateadsystem.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {
        Tests tests = new Tests();
        tests.createUser();
    }
}
