package com.proyecto.soa.model.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String descripcion;
    private Integer priority;
    private LocalDate limitDate;
}
