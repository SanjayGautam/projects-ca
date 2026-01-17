package com.example.projects.api.mapper;

import com.example.projects.api.dto.ProjectCreateRequest;
import com.example.projects.api.dto.ProjectResponse;
import com.example.projects.model.Project;

public class ProjectMapper {
    public Project toEntity(ProjectCreateRequest request) {
        if (request == null) {
            return null;
        }
        Project project = new Project();
        project.setProjectTypeId(request.getProjectTypeId());
        project.setStartDate(request.getStartDate());
        return project;
    }

    public ProjectResponse toResponse(Project project) {
        if (project == null) {
            return null;
        }
        return new ProjectResponse(project.getId(), project.getProjectTypeId(), project.getStartDate());
    }
}
