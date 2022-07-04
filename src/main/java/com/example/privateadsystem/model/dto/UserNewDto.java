package com.example.privateadsystem.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserNewDto {


    @NotBlank
    private String username;

    @NotBlank
    private String password;

}