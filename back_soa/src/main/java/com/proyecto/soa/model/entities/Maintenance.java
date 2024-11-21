package com.proyecto.soa.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class Maintenance {
    @Column(name = "usuario_creacion")
    private String userCreate;

    @Column(name = "usuario_actualizacion")
    private String userUpdate;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",name = "fecha_creacion")
    private LocalDateTime dateCreate;

    @Column(name = "fecha_actualizacion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime dateUpdate;

    @Column(name = "estado", columnDefinition = "TINYINT DEFAULT 1")
    private Integer status;
}
