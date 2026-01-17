package com.example.projects.repository;

import com.example.projects.model.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {
}
