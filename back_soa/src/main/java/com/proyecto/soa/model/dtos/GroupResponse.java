package com.proyecto.soa.model.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponse {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    private TableKanbanResponse tableKanbanResponse;
    private List<UserResponse> users;

}
