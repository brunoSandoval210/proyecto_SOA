package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.UserCreateRequest;
import com.proyecto.soa.model.dtos.UserEmail;
import com.proyecto.soa.model.dtos.UserResponse;
import com.proyecto.soa.model.dtos.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserResponse> findAll(Pageable pageable);
    UserResponse findById(Long id);
    List<UserResponse> findByEmail(String email);
    List<UserEmail> findByEmailLike(String email);
    UserResponse save(UserCreateRequest user);
    void deleteById(Long id);
    UserResponse update(UserUpdateRequest user, Long id);
}
