package com.proyecto.soa.userService;

import static jakarta.persistence.GenerationType.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "dni")
    private String dni;

    @Column(name = "telefono")
    private String phone;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    //Relacion de muchos a muchos
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    //Tabla intermedia
    @JoinTable(
            name="usuarios_roles",
            joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn(name="rol_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id","rol_id"})
    )

    private Set<Role> roles =  new HashSet<>();
}
