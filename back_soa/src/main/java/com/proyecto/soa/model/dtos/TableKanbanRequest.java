package com.proyecto.soa.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TableKanbanRequest {
    private String name;
    private List<ColumnTableResponse> columns;

}
