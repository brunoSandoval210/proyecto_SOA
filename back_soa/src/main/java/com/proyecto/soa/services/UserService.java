package com.proyecto.soa.services;

import com.proyecto.soa.model.dtos.UserCreate;
import com.proyecto.soa.model.dtos.UserUpdateDTO;
import com.proyecto.soa.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
    User save(UserCreate user);
    void deleteById(Long id);
    Optional<User> update(UserUpdateDTO user, Long id);
}
