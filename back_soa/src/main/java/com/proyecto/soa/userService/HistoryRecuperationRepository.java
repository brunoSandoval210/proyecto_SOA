package com.proyecto.soa.repositories;

import com.proyecto.soa.model.entities.HistoryRecuperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRecuperationRepository extends JpaRepository<HistoryRecuperation, Integer> {
    HistoryRecuperation findByCode(String code);
}
