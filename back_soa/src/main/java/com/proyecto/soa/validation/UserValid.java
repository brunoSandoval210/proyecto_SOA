package com.proyecto.soa.validation;

import com.proyecto.soa.model.entities.User;

public interface UserValid {
    void validUser(Long id);
    User validUserEmail(String email);
}
