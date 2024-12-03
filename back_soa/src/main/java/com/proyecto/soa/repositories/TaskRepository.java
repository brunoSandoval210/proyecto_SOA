package com.proyecto.soa.repositories;

import com.proyecto.soa.model.entities.Task;
import com.proyecto.soa.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
