package com.br.manager.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(

        @NotBlank
        @Email(message = "enter a valid email address for login")
        String login,

        String password
) {
}
