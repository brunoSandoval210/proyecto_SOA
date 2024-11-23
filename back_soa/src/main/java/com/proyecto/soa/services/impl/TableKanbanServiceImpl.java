package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.TableKanbanResponse;
import com.proyecto.soa.model.dtos.TableRequest;
import com.proyecto.soa.model.entities.TableKanban;
import com.proyecto.soa.repositories.TableKanbanRepository;
import com.proyecto.soa.services.TableKanbanService;
import com.proyecto.soa.validation.TableKanbanValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TableKanbanServiceImpl implements TableKanbanService {
    private final ModelMapper modelMapper;
    private final TableKanbanRepository tableKanbanRepository;
    private final TableKanbanValid tableKanbanValid;

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
        System.out.println(tableRequest.getName() + tableRequest.getUserId() + tableRequest.getGroupId());
        TableKanban tableKanban = tableKanbanValid.validCreateTableKanban(
                tableRequest.getUserId(), tableRequest.getGroupId(), tableRequest.getName());
        tableKanbanRepository.save(tableKanban);
        return modelMapper.map(tableKanban, TableKanbanResponse.class);
    }


}
