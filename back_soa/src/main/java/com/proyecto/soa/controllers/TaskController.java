package com.proyecto.soa.controllers;

import com.proyecto.soa.model.dtos.TaskRequest;
import com.proyecto.soa.model.dtos.TaskResponse;
import com.proyecto.soa.model.dtos.UserResponse;
import com.proyecto.soa.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTask(@RequestBody TaskRequest taskRequest){
        try {
            return ResponseEntity.ok(taskService.createTask(taskRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar la tarea" + e);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody TaskRequest taskRequest){
        try {
            return ResponseEntity.ok(taskService.updateTask(taskRequest,id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar la tarea" + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            TaskResponse task = taskService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> showByUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(taskService.findTaskByUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar las tareas del usuario" + e);
        }
    }
}
