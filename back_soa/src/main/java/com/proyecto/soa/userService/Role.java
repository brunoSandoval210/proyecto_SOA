package com.proyecto.soa.userService;

import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    @Column(name = "nombre")
    String name;
}
