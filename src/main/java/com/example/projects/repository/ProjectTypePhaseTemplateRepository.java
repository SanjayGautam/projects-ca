package com.example.projects.repository;

import com.example.projects.model.ProjectTypePhaseTemplate;
import java.util.List;

public interface ProjectTypePhaseTemplateRepository {
    List<ProjectTypePhaseTemplate> findByProjectTypeId(Long projectTypeId);
}
