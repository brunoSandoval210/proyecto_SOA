package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.TableKanbanResponse;
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
        if (tableKanban != null) {
            return modelMapper.map(tableKanban, TableKanbanResponse.class);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public TableKanbanResponse getFindByGroup(Long id) {
        TableKanban tableKanban = tableKanbanRepository.findByGroup_Id(id);
        if (tableKanban != null) {
            return modelMapper.map(tableKanban, TableKanbanResponse.class);
        }
        return null;
    }

    @Transactional
    @Override
    public TableKanbanResponse save(Long userid,Long groupId, String name) {
        TableKanban tableKanban = tableKanbanValid.validCreateTableKanban(userid, groupId, name);
        tableKanbanRepository.save(tableKanban);
        return null;
    }
}
