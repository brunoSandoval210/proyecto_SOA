package com.proyecto.soa.services.impl;

import com.proyecto.soa.model.dtos.*;
import com.proyecto.soa.model.entities.User;
import com.proyecto.soa.repositories.UserRepository;
import com.proyecto.soa.services.TableKanbanService;
import com.proyecto.soa.services.TaskService;
import com.proyecto.soa.services.UserService;
import com.proyecto.soa.validation.UserValid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserValid userValid;
    private final PasswordEncoder passwordEncoder;
    private final TableKanbanService tableKanbanService;

    @Transactional(readOnly = true)
    @Override
    public Page<UserResponse> findAll(Pageable pageable) {

        Page<User> users = userRepository.findAll(pageable);

        return users.map(user -> modelMapper.map(user, UserResponse.class));
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse findById(Long id) {

        Optional<User> user = userRepository.findById(id);

        return user.map(value -> modelMapper.map(value, UserResponse.class)).orElse(null);
    }

    @Override
    public List<UserResponse> findByEmail(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        return user.map(value -> List.of(modelMapper.map(value, UserResponse.class))).orElse(null);
    }

    @Transactional
    @Override
    public UserResponse save(UserCreateRequest userRequest) {

        User user = userValid.validUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setStatus(1);
        user= userRepository.save(user);

        //Crear tabla Kanban para el usuario
        TableRequest tableRequest = new TableRequest();
        tableRequest.setUserId(user.getId());
        tableRequest.setName("Tablero de " + user.getName());
        TableKanbanResponse tableKanbanResponse = tableKanbanService.save(tableRequest);

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setTableKanban(tableKanbanResponse);

        return userResponse;
    }

    @Transactional
    @Override
    public UserResponse update(UserUpdateRequest userUpdateRequest, Long id) {
        Optional<User> user=userRepository.findById(id);

        if(user.isPresent()){
            User userUpdate= user.get();
            userUpdate.setName(userUpdateRequest.getName());
            userUpdate.setLastname(userUpdateRequest.getLastname());
            userUpdate.setEmail(userUpdateRequest.getEmail());
            return modelMapper.map(userUpdate, UserResponse.class);
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
