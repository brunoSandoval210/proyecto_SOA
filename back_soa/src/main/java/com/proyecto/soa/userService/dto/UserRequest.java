package com.proyecto.soa.userService.dto;

import com.proyecto.soa.userService.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserRequest(
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        String dni,
        @NotBlank
        String phone,
        @NotNull
        String email,
        @NotNull
        String password,
        @NotNull
        @Size(max = 2, message = "El usuario no puede tener mas de 2 roles")
        List<String> roles
) {
}
