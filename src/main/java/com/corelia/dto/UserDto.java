package com.corelia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserDto {

    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6,message = "Password minimum length must be greater than 6 chars")
    private String password;

    private List<UserContactDto> contacts;
}
