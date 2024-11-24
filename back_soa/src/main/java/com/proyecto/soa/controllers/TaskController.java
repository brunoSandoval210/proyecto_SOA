package com.proyecto.soa.controllers;

import com.proyecto.soa.model.dtos.TaskRequest;
import com.proyecto.soa.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> saveTask(@PathVariable Long id,@RequestBody TaskRequest taskRequest){
        try {
            return ResponseEntity.ok(taskService.updateTask(taskRequest,id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar la tarea" + e);
        }
    }
}
