package com.proyecto.soa.repositories;

import com.proyecto.soa.model.entities.TableKanban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableKanbanRepository extends JpaRepository<TableKanban,Integer> {
    TableKanban findByUser_Id(Long id);
    TableKanban findByGroup_Id(Long id);
}
