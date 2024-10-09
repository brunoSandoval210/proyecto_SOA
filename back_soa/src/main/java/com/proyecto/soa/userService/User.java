package com.proyecto.soa.userService;

import static jakarta.persistence.GenerationType.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastname;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "dni")
    private String dni;

    //Relacion de muchos a muchos
    @JsonIgnoreProperties
    @ManyToMany(fetch=FetchType.EAGER)
    //Tabla intermedia
    @JoinTable(
            name="usuario_rol",
            joinColumns = {@JoinColumn(name="usuario_id")},
            inverseJoinColumns = {@JoinColumn(name="rol_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","rol_id"})}
    )
    private List<Role> roles;
}
