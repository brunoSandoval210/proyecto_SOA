package com.proyecto.soa.services;


import com.proyecto.soa.model.dtos.ColumnsTableResponse;

public interface ColumnService {
    ColumnsTableResponse findById(Long id);
}
