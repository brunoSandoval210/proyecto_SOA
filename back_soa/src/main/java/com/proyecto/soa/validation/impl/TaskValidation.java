package com.proyecto.soa.validation.impl;

import com.proyecto.soa.model.dtos.TaskRequest;
import com.proyecto.soa.model.entities.Task;
import com.proyecto.soa.repositories.ColumnTableRepository;
import com.proyecto.soa.repositories.TaskRepository;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.validation.TaskValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskValidation implements TaskValid {

    private final UserRepository userRepository;
    private final ColumnTableRepository columnTableRepository;
    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;

    @Override
    public Task validCreateTask(TaskRequest taskRequest) {
        if(taskRequest.getUser() == null){
            throw new IllegalArgumentException("La tarea debe ser asignada a un usuario");
        }
        Task task = new Task();
        if(userRepository.existsById(taskRequest.getUser())){
            task.setAssignedUser(userRepository.getById(taskRequest.getUser()));
        }

        if (taskRequest.getColumn() != null) {
            task.setColumnsTable(columnTableRepository.findById(taskRequest.getColumn())
                    .orElseThrow(() -> new IllegalArgumentException("El ID de la columna no se encontró")));
        } else {
            throw new IllegalArgumentException("El ID de la columna no puede ser nulo");
        }

        modelMapper.map(taskRequest, task);
        return task;
    }

    @Override
    public Task validUpdateTask(TaskRequest taskRequest, Long id) {

        if(taskRequest.getUser() == null){
            throw new IllegalArgumentException("La tarea debe ser asignada a un usuario");
        }
        Task task = new Task();
        if(userRepository.existsById(taskRequest.getUser())){
            task.setAssignedUser(userRepository.getById(taskRequest.getUser()));
        }

        if (taskRequest.getColumn() != null) {
            task.setColumnsTable(columnTableRepository.findById(taskRequest.getColumn())
                    .orElseThrow(() -> new IllegalArgumentException("El ID de la columna no se encontró")));
        } else {
            throw new IllegalArgumentException("El ID de la columna no puede ser nulo");
        }

        return null;
    }
}
