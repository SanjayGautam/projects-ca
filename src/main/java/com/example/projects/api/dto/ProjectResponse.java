package com.example.projects.api.dto;

import java.time.LocalDate;

public class ProjectResponse {
    private Long id;
    private Long projectTypeId;
    private LocalDate startDate;

    public ProjectResponse() {
    }

    public ProjectResponse(Long id, Long projectTypeId, LocalDate startDate) {
        this.id = id;
        this.projectTypeId = projectTypeId;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
