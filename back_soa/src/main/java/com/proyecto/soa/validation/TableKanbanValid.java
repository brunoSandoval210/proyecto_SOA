package com.proyecto.soa.validation;

import com.proyecto.soa.model.dtos.TableKanbanResponse;
import com.proyecto.soa.model.entities.TableKanban;

public interface TableKanbanValid {
    TableKanban validCreateTableKanban(Long userid, Long groupId, String name);
}
