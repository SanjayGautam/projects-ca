package com.example.projects.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_type_phase_templates")
public class ProjectTypePhaseTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_type_id")
    private Long projectTypeId;
    @Column(name = "name")
    private String name;
    @Column(name = "default_start_offset_days")
    private Integer defaultStartOffsetDays;
    @Column(name = "default_duration_days")
    private Integer defaultDurationDays;

    public ProjectTypePhaseTemplate() {
    }

    public ProjectTypePhaseTemplate(
            Long id,
            Long projectTypeId,
            String name,
            Integer defaultStartOffsetDays,
            Integer defaultDurationDays
    ) {
        this.id = id;
        this.projectTypeId = projectTypeId;
        this.name = name;
        this.defaultStartOffsetDays = defaultStartOffsetDays;
        this.defaultDurationDays = defaultDurationDays;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefaultStartOffsetDays() {
        return defaultStartOffsetDays;
    }

    public void setDefaultStartOffsetDays(Integer defaultStartOffsetDays) {
        this.defaultStartOffsetDays = defaultStartOffsetDays;
    }

    public Integer getDefaultDurationDays() {
        return defaultDurationDays;
    }

    public void setDefaultDurationDays(Integer defaultDurationDays) {
        this.defaultDurationDays = defaultDurationDays;
    }
}
