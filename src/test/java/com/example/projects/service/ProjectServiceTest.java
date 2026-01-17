package com.example.projects.service;

import com.example.projects.api.dto.ProjectCreateRequest;
import com.example.projects.api.dto.ProjectResponse;
import com.example.projects.api.mapper.ProjectMapper;
import com.example.projects.model.Phase;
import com.example.projects.model.Project;
import com.example.projects.model.ProjectTypePhaseTemplate;
import com.example.projects.repository.PhaseRepository;
import com.example.projects.repository.ProjectRepository;
import com.example.projects.repository.ProjectTypePhaseTemplateRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectServiceTest {
    @Test
    void createProjectWithoutTemplatesReturnsSavedProject() {
        InMemoryProjectRepository projectRepository = new InMemoryProjectRepository();
        ProjectService service = new ProjectService(
                projectRepository,
                projectTypeId -> Collections.emptyList(),
                new InMemoryPhaseRepository(),
                new ProjectMapper()
        );
        Project project = new Project(null, null, LocalDate.of(2024, 1, 10));

        Project savedProject = service.createProject(project);

        assertEquals(1L, savedProject.getId());
        assertEquals(1, projectRepository.savedProjects.size());
    }

    @Test
    void createProjectBuildsPhasesFromTemplates() {
        InMemoryProjectRepository projectRepository = new InMemoryProjectRepository();
        InMemoryPhaseRepository phaseRepository = new InMemoryPhaseRepository();
        List<ProjectTypePhaseTemplate> templates = List.of(
                new ProjectTypePhaseTemplate(10L, 2L, "Kickoff", 0, 2),
                new ProjectTypePhaseTemplate(11L, 2L, "Build", 3, null)
        );
        ProjectService service = new ProjectService(
                projectRepository,
                projectTypeId -> templates,
                phaseRepository,
                new ProjectMapper()
        );
        Project project = new Project(null, 2L, LocalDate.of(2024, 2, 1));

        Project savedProject = service.createProject(project);

        assertEquals(2, phaseRepository.savedPhases.size());
        Phase kickoff = phaseRepository.savedPhases.get(0);
        assertEquals("Kickoff", kickoff.getName());
        assertEquals(LocalDate.of(2024, 2, 1), kickoff.getStartDate());
        assertEquals(LocalDate.of(2024, 2, 3), kickoff.getEndDate());
        Phase build = phaseRepository.savedPhases.get(1);
        assertEquals(LocalDate.of(2024, 2, 4), build.getStartDate());
        assertNull(build.getEndDate());
        assertEquals(savedProject.getId(), build.getProjectId());
    }

    @Test
    void createProjectThrowsWhenDefaultsRequireMissingStartDate() {
        ProjectService service = new ProjectService(
                project -> project,
                projectTypeId -> List.of(new ProjectTypePhaseTemplate(10L, 1L, "Kickoff", 1, null)),
                phase -> phase,
                new ProjectMapper()
        );
        Project project = new Project(null, 1L, null);

        assertThrows(IllegalArgumentException.class, () -> service.createProject(project));
    }

    @Test
    void createProjectFromRequestReturnsResponse() {
        InMemoryProjectRepository projectRepository = new InMemoryProjectRepository();
        ProjectService service = new ProjectService(
                projectRepository,
                projectTypeId -> Collections.emptyList(),
                new InMemoryPhaseRepository(),
                new ProjectMapper()
        );
        ProjectCreateRequest request = new ProjectCreateRequest(5L, LocalDate.of(2024, 3, 3));

        ProjectResponse response = service.createProject(request);

        assertEquals(1L, response.getId());
        assertEquals(5L, response.getProjectTypeId());
    }

    private static final class InMemoryProjectRepository implements ProjectRepository {
        private long nextId = 1L;
        private final List<Project> savedProjects = new ArrayList<>();

        @Override
        public Project save(Project project) {
            if (project.getId() == null) {
                project.setId(nextId++);
            }
            savedProjects.add(project);
            return project;
        }
    }

    private static final class InMemoryPhaseRepository implements PhaseRepository {
        private final List<Phase> savedPhases = new ArrayList<>();

        @Override
        public Phase save(Phase phase) {
            savedPhases.add(phase);
            return phase;
        }
    }
}
