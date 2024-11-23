package com.proyecto.soa.validation.impl;

import com.proyecto.soa.model.dtos.TableKanbanResponse;
import com.proyecto.soa.model.entities.TableKanban;
import com.proyecto.soa.repositories.GroupRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.validation.TableKanbanValid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TableKanbanValidation implements TableKanbanValid {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public TableKanban validCreateTableKanban(Long userid, Long groupId, String name) {
        TableKanban tableKanban = new TableKanban();
        boolean existUser= userRepository.existsById(userid);
        boolean existGroup= groupRepository.existsById(groupId);

        if(existUser && existGroup){
            throw new RuntimeException("Una tabla kanban debe tener un usuario o un grupo, no ambos");
        }
        tableKanban.setName(name);
        tableKanban.setUser(userid != null ? userRepository.getById(userid) : null);
        tableKanban.setGroup(groupId != null ? groupRepository.getById(groupId) : null);

        return tableKanban;
    }
}
