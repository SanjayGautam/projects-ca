package com.example.projects.repository;

import com.example.projects.model.Phase;

public interface PhaseRepository {
    Phase save(Phase phase);
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
}
