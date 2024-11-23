package com.proyecto.soa.controllers;

import com.proyecto.soa.model.dtos.UserCreateRequest;
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
    public Page<User> listPageable(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page,10);
        return userService.findAll(pageable);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<User> user=userService.findById(id);
        if(user.isPresent()){
            //Se retorna un 200 porque se encontro el usuario
            return ResponseEntity.status(HttpStatus.OK).body(user.orElseThrow());
        }
        //
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("error","El usuario no se encontro por el id: "+id));
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

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        Optional<User> optionalUser=userService.findById(id);
        if(optionalUser.isPresent()){
            userService.deleteById(id);
            //Se retorna un 204 porque no hay contenido
            return ResponseEntity.status((HttpStatus.NO_CONTENT)).build();
        }
        //Se retorna un 404 porque no se encontro el usuario
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("user/{id}")
    public ResponseEntity<User> update (@PathVariable Long id, @RequestBody UserUpdateRequest user){
        Optional<User> userUpdate = userService.update(user, id);
        if (userUpdate.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userUpdate.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
