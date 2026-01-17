package com.example.projects.service;

import com.example.projects.model.Phase;
import com.example.projects.model.Project;
import com.example.projects.model.ProjectTypePhaseTemplate;
import com.example.projects.repository.PhaseRepository;
import com.example.projects.repository.ProjectRepository;
import com.example.projects.repository.ProjectTypePhaseTemplateRepository;
import com.example.projects.api.dto.ProjectCreateRequest;
import com.example.projects.api.dto.ProjectResponse;
import com.example.projects.api.mapper.ProjectMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectTypePhaseTemplateRepository templateRepository;
    private final PhaseRepository phaseRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(
            ProjectRepository projectRepository,
            ProjectTypePhaseTemplateRepository templateRepository,
            PhaseRepository phaseRepository,
            ProjectMapper projectMapper
    ) {
        this.projectRepository = Objects.requireNonNull(projectRepository, "projectRepository");
        this.templateRepository = Objects.requireNonNull(templateRepository, "templateRepository");
        this.phaseRepository = Objects.requireNonNull(phaseRepository, "phaseRepository");
        this.projectMapper = Objects.requireNonNull(projectMapper, "projectMapper");
    }

    public ProjectResponse createProject(ProjectCreateRequest request) {
        Project project = projectMapper.toEntity(request);
        Project savedProject = createProject(project);
        return projectMapper.toResponse(savedProject);
    }

    public Project createProject(Project project) {
        Objects.requireNonNull(project, "project");
        Project savedProject = projectRepository.save(project);
        if (savedProject.getProjectTypeId() == null) {
            return savedProject;
        }

        List<ProjectTypePhaseTemplate> templates =
                templateRepository.findByProjectTypeId(savedProject.getProjectTypeId());
        for (ProjectTypePhaseTemplate template : templates) {
            Phase phase = buildPhase(savedProject, template);
            phaseRepository.save(phase);
        }
        return savedProject;
    }

    private Phase buildPhase(Project project, ProjectTypePhaseTemplate template) {
        Phase phase = new Phase();
        phase.setProjectId(project.getId());
        phase.setName(template.getName());

        Integer offsetDays = template.getDefaultStartOffsetDays();
        Integer durationDays = template.getDefaultDurationDays();
        if (offsetDays != null || durationDays != null) {
            LocalDate projectStart = project.getStartDate();
            if (projectStart == null) {
                throw new IllegalArgumentException("project start date is required for phase defaults");
            }
            if (offsetDays != null && offsetDays < 0) {
                throw new IllegalArgumentException("default start offset days cannot be negative");
            }
            if (durationDays != null && durationDays < 0) {
                throw new IllegalArgumentException("default duration days cannot be negative");
            }

            LocalDate phaseStart = projectStart.plusDays(offsetDays == null ? 0 : offsetDays);
            phase.setStartDate(phaseStart);
            if (durationDays != null) {
                LocalDate phaseEnd = phaseStart.plusDays(durationDays);
                if (phaseEnd.isBefore(phaseStart)) {
                    throw new IllegalArgumentException("phase end date must be on or after start date");
                }
                phase.setEndDate(phaseEnd);
            }
        }

        return phase;
    }
}
