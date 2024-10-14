package com.proyecto.soa.userService;

import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
}
