package com.example.projects.repository;

import com.example.projects.model.Project;

public interface ProjectRepository {
    Project save(Project project);
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
