package com.proyecto.soa.model.dtos;

import com.proyecto.soa.model.entities.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    private String email;
    @NotNull
    private Role role;
    @NotNull
    private Long tableId;
}
