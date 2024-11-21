package com.proyecto.soa.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "tarea")
@Entity
@Data
public class Task extends Maintenance{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "prioridad")
    private Integer priority;

    @Column(name = "fecha_limite")
    private LocalDate limitDate;

    @ManyToOne
    @JoinColumn(name = "columna_id", nullable = false)
    private ColumnTable columnsTable;

    @ManyToOne
    @JoinColumn(name = "usuario_asignado_id")
    private User assignedUser;
}
