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
    @NotNull
    private Long tableId;
    @NotNull
    private List<UserResponse> users;

}
