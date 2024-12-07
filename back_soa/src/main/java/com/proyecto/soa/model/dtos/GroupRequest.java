package com.proyecto.soa.model.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupRequest {
    private String name;
    @NotNull
    private List<Long> usersId;
}
