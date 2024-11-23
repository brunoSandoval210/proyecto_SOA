package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.TableKanbanResponse;

public interface TableKanbanService {
    TableKanbanResponse getFindByUser(Long id);
    TableKanbanResponse getFindByGroup(Long id);
    TableKanbanResponse save(Long userid,Long groupId, String name);
}
