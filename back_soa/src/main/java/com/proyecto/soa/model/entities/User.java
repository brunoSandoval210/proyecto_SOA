package com.proyecto.soa.model.entities;

import static jakarta.persistence.GenerationType.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.soa.model.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastname;

    @Email
    @Column(name = "email")
//    @NotEmpty
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "dni")
    private String dni;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }
}
