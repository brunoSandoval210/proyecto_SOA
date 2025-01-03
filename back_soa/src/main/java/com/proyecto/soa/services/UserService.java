package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.UserCreateRequest;
import com.proyecto.soa.model.dtos.UserResponse;
import com.proyecto.soa.model.dtos.UserUpdateRequest;
import com.proyecto.soa.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
    UserResponse save(UserCreateRequest user);
    void deleteById(Long id);
    UserResponse update(UserUpdateRequest user, Long id);
}
