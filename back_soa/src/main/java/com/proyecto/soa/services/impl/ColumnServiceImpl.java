package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.ColumnsTableResponse;
import com.proyecto.soa.model.dtos.TaskResponse;
import com.proyecto.soa.model.entities.ColumnTable;
import com.proyecto.soa.model.entities.Task;
import com.proyecto.soa.repositories.ColumnTableRepository;
import com.proyecto.soa.repositories.TaskRepository;
import com.proyecto.soa.services.ColumnService;
import com.proyecto.soa.services.TaskService;
import com.proyecto.soa.validation.TaskValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColumnServiceImpl implements ColumnService {

    private final ColumnTableRepository columnTableRepository;
    private final ModelMapper modelMapper;

    @Override
    public ColumnsTableResponse findById(Long id) {
        Optional<ColumnTable> column = columnTableRepository.findById(id);
        ColumnsTableResponse columnResponse = modelMapper.map(column, ColumnsTableResponse.class);
        return columnResponse;
    }
}
