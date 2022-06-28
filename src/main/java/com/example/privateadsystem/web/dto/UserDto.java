package com.example.privateadsystem.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String dateOfBirth;
}
