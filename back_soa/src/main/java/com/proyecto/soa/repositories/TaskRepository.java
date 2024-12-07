package com.proyecto.soa.repositories;

import com.proyecto.soa.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByAssignedUser_Id(Long id);
}
