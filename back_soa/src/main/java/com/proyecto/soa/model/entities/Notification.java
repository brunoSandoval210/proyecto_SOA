package com.proyecto.soa.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "notificaciones")
public class Notification extends Maintenance{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mensaje", nullable = false)
    private String message;

    @Column(name = "fecha", nullable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;
}
