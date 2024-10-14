package com.proyecto.soa.userService;

import com.proyecto.soa.userService.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserRequest userRequest){
        return new ResponseEntity<>(this.userService.registerUser(userRequest), HttpStatus.CREATED);
    }
}
