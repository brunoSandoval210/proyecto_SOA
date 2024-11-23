package com.proyecto.soa.validation;

import com.proyecto.soa.model.dtos.TaskRequest;
import com.proyecto.soa.model.entities.Task;

public interface TaskValid {
    Task validCreateTask(TaskRequest taskRequest);
    Task validUpdateTask(TaskRequest taskRequest, Long id);
}
