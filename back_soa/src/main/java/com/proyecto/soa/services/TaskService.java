package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.TaskRequest;
import com.proyecto.soa.model.dtos.TaskResponse;
import com.proyecto.soa.model.dtos.TaskUserReponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse updateTask(TaskRequest taskRequest, Long id);
    TaskResponse findById(Long id);
    List<TaskUserReponse> findTaskByUser(Long id);
}
