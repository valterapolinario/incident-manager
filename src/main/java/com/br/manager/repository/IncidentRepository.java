package com.br.manager.repository;

import com.br.manager.model.IncidentDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<IncidentDB, Long> {

    List<IncidentDB> findTop20ByOrderByCreatedAtDesc();
}
