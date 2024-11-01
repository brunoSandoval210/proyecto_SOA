package com.proyecto.soa.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    @Size(max = 45,message = "El nombre no puede tener más de 45 caracteres")
    private String name;
    @Size(max = 45, message = "El apellido no puede tener más de 45 caracteres")
    private String lastname;
    @Size(max = 45)
    @Email(message = "El email debe ser válido")
    private String email;
}
