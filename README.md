# Project Management System (Phases/Tasks/Checklist)

This repository captures a proposed starting plan for a project management system that supports:

- Project types with predefined phases (templates)
- Per-project phases that can overlap and are date-driven
- Tasks per phase, each with a checklist
- React UI with Material UI
- Oracle-backed REST service layer

## 1. Recommended Stack

**Frontend**
- React + TypeScript
- Material UI (MUI)
- React Router for navigation
- React Query (or SWR) for data fetching/caching

**Backend**
- Java + Spring Boot (recommended) or Node.js (if preferred)
- Oracle Database
- REST API with validation, paging, and audit fields

## 2. Core Domain Model

### ProjectType
- `id`
- `name`
- `description`

### ProjectTypePhaseTemplate
- `id`
- `project_type_id`
- `name`
- `default_order` (optional if ordering is needed)
- `default_start_offset_days` (optional)
- `default_duration_days` (optional)

### Project
- `id`
- `project_type_id`
- `name`
- `description`
- `start_date`
- `end_date`

### Phase
- `id`
- `project_id`
- `name`
- `start_date`
- `end_date`
- `order_index` (optional)
- `status`

### Task
- `id`
- `phase_id`
- `title`
- `description`
- `start_date`
- `end_date`
- `status`
- `priority`

### ChecklistItem
- `id`
- `task_id`
- `title`
- `is_complete`

## 3. REST API (Initial Endpoints)

### Project Types
- `GET /project-types`
- `POST /project-types`
- `GET /project-types/{id}`
- `PUT /project-types/{id}`
- `POST /project-types/{id}/phase-templates`

### Projects
- `GET /projects`
- `POST /projects`
- `GET /projects/{id}`
- `PUT /projects/{id}`
- `POST /projects/{id}/phases` (manual creation or auto-instantiate from templates)

### Phases
- `GET /phases/{id}`
- `PUT /phases/{id}`
- `GET /projects/{id}/phases`

### Tasks
- `POST /phases/{id}/tasks`
- `GET /tasks/{id}`
- `PUT /tasks/{id}`

### Checklist Items
- `POST /tasks/{id}/checklist`
- `PUT /checklist/{id}`

## 4. Key Rules to Confirm

- Are phase dependencies required or are overlapping phases always allowed?
- Are task dependencies needed or simple scheduling only?
- Are start/end dates mandatory for phases and tasks?
- Do you need audit history (status changes, user actions)?

## 5. Suggested Milestones

**Milestone 1: Setup & Templates**
- Define Project Types
- Define Phase Templates per Project Type

**Milestone 2: Project Creation & Scheduling**
- Create Projects
- Auto-generate phases from templates
- Basic phase scheduling by dates

**Milestone 3: Tasks & Checklist**
- Create tasks per phase
- Create checklist items
- Status updates

**Milestone 4: UI Enhancements**
- Phase timeline visualization
- Overlap indicators
- Simple reporting (overdue tasks, completed phases)

## 6. Next Step Proposal

If you want, I can scaffold:

- A Spring Boot service wired to Oracle (with JPA models + REST endpoints)
- A React + MUI UI shell (project list + project detail + phases + tasks)
- Database DDL scripts for Oracle

Let me know the preferred backend language (Spring Boot or Node) and any constraints around Oracle schema standards.

## 7. Oracle DDL Script

The initial Oracle schema is available here:

- `db/oracle/ddl.sql`
