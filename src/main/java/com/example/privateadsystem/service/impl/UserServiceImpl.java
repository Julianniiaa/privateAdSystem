package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.exception.DataBaseException;
import com.example.privateadsystem.model.Role;
import com.example.privateadsystem.model.User;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.UserService;
import com.example.privateadsystem.model.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(UserDto userDto) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateOfBirth = format.parse(userDto.getDateOfBirth());
        User user = User.builder().username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .dateOfBirth(dateOfBirth)
                .roles(List.of(new Role("USER")))
                .build();
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User not found for id::" + id));
    }

    @Override
    public User updateUser(long id, UserDto userDto) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateOfBirth = format.parse(userDto.getDateOfBirth());
        User user = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User not found for id::" + id));
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setDateOfBirth(dateOfBirth);
        return userRepository.save(user);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserLoginAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
            logger.info("No such password {}", password);
            throw new DataBaseException("No such password");
        }
        logger.info("No such username");
        throw new DataBaseException("No such username");
    }

    @Override
    public String getUsername(long id) {
        return userRepository.findByIdUser(id).getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
        {
            logger.info("Invalid username or password");
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
