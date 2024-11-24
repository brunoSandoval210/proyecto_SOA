package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.TaskRequest;
import com.proyecto.soa.model.dtos.TaskResponse;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse updateTask(TaskRequest taskRequest, Long id);
}
