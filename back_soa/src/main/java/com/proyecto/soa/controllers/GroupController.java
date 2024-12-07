package com.proyecto.soa.controllers;

import com.proyecto.soa.model.dtos.GroupRequest;
import com.proyecto.soa.model.dtos.GroupResponse;
import com.proyecto.soa.model.dtos.GroupsByUser;
import com.proyecto.soa.services.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable Long id){
        try {
            List<GroupResponse> group = groupService.getGroupsById(id);
            return ResponseEntity.status(HttpStatus.OK).body(group);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> listByUser(@PathVariable Long id){
        try {
            List<GroupsByUser> groups = groupService.getGroupsByUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(groups);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupRequest groupRequest, BindingResult result){
        try{
            if(result.hasErrors()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
            }
            GroupResponse groupResponse = groupService.createGroup(groupRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(groupResponse);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error",e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id){
        try{
            groupService.deleteGroup(id);
            return ResponseEntity.status(HttpStatus.OK).body("Grupo eliminado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error",e.getMessage()));
        }
    }
}
