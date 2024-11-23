package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.TaskRequest;
import com.proyecto.soa.model.dtos.TaskResponse;
import com.proyecto.soa.model.entities.Task;
import com.proyecto.soa.repositories.TaskRepository;
import com.proyecto.soa.services.TaskService;
import com.proyecto.soa.services.UserService;
import com.proyecto.soa.validation.TaskValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskValid taskValid;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskValid.validCreateTask(taskRequest);
        task = taskRepository.save(task);

        TaskResponse taskResponse = modelMapper.map(task, TaskResponse.class);
        taskResponse.setNameUser(task.getAssignedUser().getName());
        taskResponse.setUserId(task.getAssignedUser().getId());
        return taskResponse;
    }

    @Transactional
    @Override
    public TaskResponse updateTask(TaskRequest taskRequest) {
//        Task task = taskValid.validUpdateTask(taskRequest);
        return null;
    }


}
