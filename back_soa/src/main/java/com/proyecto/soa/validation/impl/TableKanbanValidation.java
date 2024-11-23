package com.proyecto.soa.validation.impl;

import com.proyecto.soa.model.dtos.TableKanbanResponse;
import com.proyecto.soa.model.entities.ColumnTable;
import com.proyecto.soa.model.entities.TableKanban;
import com.proyecto.soa.repositories.GroupRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.validation.TableKanbanValid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TableKanbanValidation implements TableKanbanValid {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public TableKanban validCreateTableKanban(Long userid, Long groupId, String name) {
        if (userid == null && groupId == null) {
            throw new IllegalArgumentException("Una tabla kanban debe tener un usuario o un grupo");
        }

        TableKanban tableKanban = new TableKanban();
        boolean existGroup = groupId != null && groupRepository.existsById(groupId);

        if (existGroup) {
            throw new RuntimeException("Una tabla kanban debe tener un usuario o un grupo, no ambos");
        }
        tableKanban.setName(name);
        tableKanban.setUser(userid != null ? userRepository.getById(userid) : null);
        tableKanban.setGroup(groupId != null ? groupRepository.getById(groupId) : null);

        //Crear columnas por defectos
        ColumnTable column1=new ColumnTable();
        column1.setName("Por asignar");
        column1.setTableKanban(tableKanban);

        ColumnTable column2=new ColumnTable();
        column2.setName("Pendiente");
        column2.setTableKanban(tableKanban);

        ColumnTable column3=new ColumnTable();
        column3.setName("En proceso");
        column3.setTableKanban(tableKanban);

        ColumnTable column4=new ColumnTable();
        column4.setName("Finalizado");
        column4.setTableKanban(tableKanban);

        tableKanban.setColumnsTable(List.of(column1,column2,column3,column4));

        return tableKanban;
    }
}
