package com.proyecto.soa.controllers;

import com.proyecto.soa.model.dtos.UserCreateRequest;
import com.proyecto.soa.model.dtos.UserEmail;
import com.proyecto.soa.model.dtos.UserResponse;
import com.proyecto.soa.model.dtos.UserUpdateRequest;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.services.EmailService;
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
@RequestMapping("")
public class UserController {
    private final UserService userService;

    @GetMapping("users/{page}")
    public Page<UserResponse> listPageable(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page,10);
        return userService.findAll(pageable);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            UserResponse user = userService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("user/emailLike")
    public ResponseEntity<?> showByEmailLike(@RequestParam String email){
        try {
            List<UserEmail> user = userService.findByEmailLike(email);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    @GetMapping("user/email")
    public ResponseEntity<?> showByEmail(@RequestParam String email){
        try {
            List<UserResponse> user = userService.findByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("registerUser")
    public ResponseEntity<?> create(@Valid @RequestBody UserCreateRequest user, BindingResult result) {

        try{
            if(result.hasErrors()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
            }
            UserResponse userResponse = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error",e.getMessage()));
        }
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest, BindingResult result){
        try{
            if(result.hasErrors()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
            }
            UserResponse userResponse = userService.update(userUpdateRequest, id);
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error",e.getMessage()));
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        UserResponse optionalUser=userService.findById(id);
        if(optionalUser!=null){
            userService.deleteById(id);
            return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
