package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.ColumnsTableResponse;
import com.proyecto.soa.model.dtos.TableKanbanResponse;
import com.proyecto.soa.model.dtos.TableRequest;
import com.proyecto.soa.model.entities.ColumnTable;
import com.proyecto.soa.model.entities.TableKanban;
import com.proyecto.soa.repositories.ColumnTableRepository;
import com.proyecto.soa.repositories.TableKanbanRepository;
import com.proyecto.soa.services.TableKanbanService;
import com.proyecto.soa.validation.TableKanbanValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TableKanbanServiceImpl implements TableKanbanService {
    private final ModelMapper modelMapper;
    private final TableKanbanRepository tableKanbanRepository;
    private final TableKanbanValid tableKanbanValid;
    private final ColumnTableRepository columnTableRepository;

    @Transactional(readOnly = true)
    @Override
    public TableKanbanResponse getFindByUser(Long id) {
        TableKanban tableKanban = tableKanbanRepository.findByUser_Id(id);
        return modelMapper.map(tableKanban, TableKanbanResponse.class);
    }

    @Transactional(readOnly = true)
    @Override
    public TableKanbanResponse getFindByGroup(Long id) {
        TableKanban tableKanban = tableKanbanRepository.findByGroup_Id(id);
        return modelMapper.map(tableKanban, TableKanbanResponse.class);


    }

    @Transactional
    @Override
    public TableKanbanResponse save(TableRequest tableRequest) {
        TableKanban tableKanban = tableKanbanValid.validCreateTableKanban(
                tableRequest.getUserId(), tableRequest.getGroupId(), tableRequest.getName());
        tableKanbanRepository.save(tableKanban);
        return modelMapper.map(tableKanban, TableKanbanResponse.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ColumnsTableResponse> getColumnsByTable(Long id) {
        List<ColumnTable> columnTables = columnTableRepository.findByTableKanban_Id(id);

        return columnTables.stream()
                .map(columnTable -> modelMapper.map(columnTable, ColumnsTableResponse.class))
                .collect(Collectors.toList());

    }
}
