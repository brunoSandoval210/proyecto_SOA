package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.*;
import com.proyecto.soa.model.entities.Task;
import com.proyecto.soa.repositories.TaskRepository;
import com.proyecto.soa.services.TaskService;
import com.proyecto.soa.validation.TaskValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
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
    public TaskResponse updateTask(TaskRequest taskRequest, Long id) {
        Task task = taskValid.validUpdateTask(taskRequest,id);

        task = taskRepository.save(task);
        TaskResponse taskResponse = modelMapper.map(task, TaskResponse.class);
        taskResponse.setNameUser(task.getAssignedUser().getName());
        taskResponse.setUserId(task.getAssignedUser().getId());
        return taskResponse;
    }

    @Transactional(readOnly = true)
    @Override
    public TaskResponse findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        TaskResponse taskResponse = modelMapper.map(task, TaskResponse.class);
        taskResponse.setColumnId(task.get().getColumnsTable().getId());
        return taskResponse;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskUserReponse> findTaskByUser(Long id) {
        List<Task> tasks = taskRepository.findByAssignedUser_Id(id);
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskUserReponse.class))
                .collect(Collectors.toList());
    }
}