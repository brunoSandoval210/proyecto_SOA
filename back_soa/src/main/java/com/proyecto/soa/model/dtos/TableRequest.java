package com.proyecto.soa.model.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TableRequest {
    private String name;
    private Long userId;
    private Long groupId;
}
