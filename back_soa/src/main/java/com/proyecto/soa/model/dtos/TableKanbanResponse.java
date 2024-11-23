package com.proyecto.soa.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TableKanbanResponse {
    private Long id;
    private String name;
    private List<ColumnTableResponse> columns;
}
