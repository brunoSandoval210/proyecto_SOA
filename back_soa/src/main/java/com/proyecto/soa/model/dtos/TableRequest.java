package com.proyecto.soa.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequest {
    private String name;
    private Long userId;
    private Long groupId;
}
