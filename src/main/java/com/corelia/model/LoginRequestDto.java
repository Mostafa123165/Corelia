package com.corelia.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequestDto {

    @NotEmpty(message = "email  is required")
    private String email;

    @NotEmpty(message = "password is required")
    private String password;
}
