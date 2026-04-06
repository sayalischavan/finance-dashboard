package com.finance.dashboard.dto;

import jakarta.validation.constraints.*;

public class UserDTO {

    @NotBlank
    public String name;

    @Email
    public String email;

    @NotNull
    public String role;
}