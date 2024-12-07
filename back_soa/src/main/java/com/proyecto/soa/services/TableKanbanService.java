package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.ColumnsTableResponse;
import com.proyecto.soa.model.dtos.TableKanbanResponse;
import com.proyecto.soa.model.dtos.TableRequest;

import java.util.List;

public interface TableKanbanService {
    TableKanbanResponse getFindByUser(Long id);
    TableKanbanResponse getFindByGroup(Long id);
    TableKanbanResponse save(TableRequest tableRequest);
    List<ColumnsTableResponse> getColumnsByTable(Long id);

}
