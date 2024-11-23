package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.TableKanbanResponse;
import com.proyecto.soa.model.dtos.TableRequest;

public interface TableKanbanService {
    TableKanbanResponse getFindByUser(Long id);
    TableKanbanResponse getFindByGroup(Long id);
    TableKanbanResponse save(TableRequest tableRequest);
}
