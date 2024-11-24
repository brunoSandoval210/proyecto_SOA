package com.proyecto.soa.model.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private RoleResponse role;
    private TableKanbanResponse tableKanban;

}
