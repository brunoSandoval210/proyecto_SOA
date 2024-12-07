package com.proyecto.soa.repositories;

import com.proyecto.soa.model.entities.ColumnTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnTableRepository extends JpaRepository<ColumnTable,Long> {
    List<ColumnTable> findByTableKanban_Id (Long id);
}
