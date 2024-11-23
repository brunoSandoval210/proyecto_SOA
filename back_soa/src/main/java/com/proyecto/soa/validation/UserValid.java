package com.proyecto.soa.validation;

import com.proyecto.soa.model.dtos.UserCreateRequest;
import com.proyecto.soa.model.entities.User;

public interface UserValid {
    User validUserEmail(UserCreateRequest userCreateRequest);
}
