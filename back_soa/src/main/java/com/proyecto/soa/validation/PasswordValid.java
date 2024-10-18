package com.proyecto.soa.validation;

public interface PasswordValid {
    void validUser(String email);
    void isValidPassword(String password, String validPassword, String email);
}
