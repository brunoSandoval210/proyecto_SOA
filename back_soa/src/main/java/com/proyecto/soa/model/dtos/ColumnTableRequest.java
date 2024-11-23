package com.proyecto.soa.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ColumnTableRequest {
    private Long id;
    private String name;
    private List<TaskResponse> tasks;

}
