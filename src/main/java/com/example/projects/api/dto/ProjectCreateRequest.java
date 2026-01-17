package com.example.projects.api.dto;

import java.time.LocalDate;

public class ProjectCreateRequest {
    private Long projectTypeId;
    private LocalDate startDate;

    public ProjectCreateRequest() {
    }

    public ProjectCreateRequest(Long projectTypeId, LocalDate startDate) {
        this.projectTypeId = projectTypeId;
        this.startDate = startDate;
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
