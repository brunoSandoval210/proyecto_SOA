package com.proyecto.soa.model.dtos;

import lombok.Data;


@Data
public class UserCreate {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String dni;
    private String role;
    private String username;
}
