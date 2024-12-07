package com.proyecto.soa.controllers;


import com.proyecto.soa.model.dtos.*;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.services.ColumnService;
import com.proyecto.soa.services.EmailService;
import com.proyecto.soa.services.TaskService;
import com.proyecto.soa.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/column")
public class ColumnController {

    private final ColumnService columnService;

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            ColumnsTableResponse column = columnService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(column);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
