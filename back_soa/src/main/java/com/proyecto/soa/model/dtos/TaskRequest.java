package com.proyecto.soa.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskRequest {
    private Long column;
    private String title;
    private String descripcion;
    private Integer priority;
    private LocalDate limitDate;
    private Long user;
}
