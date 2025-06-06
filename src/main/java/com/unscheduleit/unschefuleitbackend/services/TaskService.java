package com.unscheduleit.unschefuleitbackend.services;

import com.unscheduleit.unschefuleitbackend.dto.CheckListItemDTO;
import com.unscheduleit.unschefuleitbackend.dto.TaskCheckListDTO;
import com.unscheduleit.unschefuleitbackend.dto.TaskDTO;
import com.unscheduleit.unschefuleitbackend.entities.ChecklistItem;
import com.unscheduleit.unschefuleitbackend.entities.Goal;
import com.unscheduleit.unschefuleitbackend.entities.Task;
import com.unscheduleit.unschefuleitbackend.repository.GoalRepo;
import com.unscheduleit.unschefuleitbackend.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final CheckListService checkListService;

    private final TaskRepo taskRepo;

    @Autowired
    private GoalRepo goalRepo;

    public TaskService(CheckListService checkListService, TaskRepo taskRepo) {
        this.checkListService = checkListService;
        this.taskRepo = taskRepo;
    }

    public void createTask(TaskDTO dto) {
        Task entity = mapToEntity(dto);
        taskRepo.save(entity);
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteTask(String taskId) {
        taskRepo.deleteById(taskId);
    }

    public void updateTask(TaskDTO dto) {
        taskRepo.save(mapToEntity(dto));
    }

    private Task mapToEntity(TaskDTO dto) {
        Task entity = new Task();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDate(dto.getDate());
        entity.setDone(dto.isDone());
        entity.setDisabled(dto.isDisabled());
        entity.setNotes(dto.getNotes());
        entity.setDifficulty(dto.getDifficulty());
        entity.setRepeats(dto.getRepeats());
        entity.setRepeatEvery(dto.getRepeatEvery());
        entity.setTags(dto.getTags());
        entity.setTimeDone(dto.getTimeDone());

        // NEW: set the parent Goal reference using the goalId from DTO
        if (dto.getGoalId() == null) {
            throw new IllegalArgumentException("Cannot create a Task without a Goal.");
        }
        // Lazily or eagerly load the Goal so that the ORM knows it already exists
        Goal goal = goalRepo.getReferenceById(dto.getGoalId());
        entity.setGoal(goal);

        return entity;
    }

    private TaskDTO mapToDto(Task entity) {
        TaskDTO dto = new TaskDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDate(),
                entity.isDone(),
                entity.isDisabled(),
                entity.getNotes(),
                entity.getDifficulty(),
                entity.getRepeats(),
                entity.getRepeatEvery(),
                entity.getTags(),
                entity.getTimeDone(),
                entity.getGoal().getId()

        );
        if (entity.getGoal() != null) {
            dto.setGoalId(entity.getGoal().getId());
        }

        // map checklist if needed…
        return dto;
    }

    public void addCheckListItem(TaskCheckListDTO dto) {

       ChecklistItem checkListItem = checkListService.getCheckListItem(dto.getCheckListId());
       Task task = taskRepo.findById(dto.getTaskId()).get();

       task.getChecklist().add(checkListItem);
       taskRepo.save(task);

    }

    public List<TaskDTO> getTasksFilteredAndSorted(
            String goalId,
            String difficulty,
            List<String> tags,
            String sortBy,
            String order
    ) {
        // 1. Build Sort as you already have:
        Sort.Direction direction = "desc".equalsIgnoreCase(order)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Sort sort = (sortBy == null || sortBy.isEmpty())
                ? Sort.unsorted()
                : Sort.by(direction, sortBy);

        // 2. Build Specification<Task> dynamically:
        Specification<Task> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // a) filter by goalId if provided
            if (goalId != null && !goalId.isEmpty()) {
                // root.get("goal") is the ManyToOne field, then get its "id"
                predicates.add(
                        cb.equal(root.get("goal").get("id"), goalId)
                );
            }

            // b) filter by difficulty if provided
            if (difficulty != null && !difficulty.isEmpty()) {
                predicates.add(cb.equal(root.get("difficulty"), difficulty));
            }

            // c) filter by tags if provided
            if (tags != null && !tags.isEmpty()) {
                List<Predicate> tagClauses = new ArrayList<>();
                for (String singleTag : tags) {
                    tagClauses.add(cb.like(root.get("tags"), "%" + singleTag + "%"));
                }
                predicates.add(cb.or(tagClauses.toArray(new Predicate[0])));
            }

            // Combine everything with AND
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        // 3. Execute:
        List<Task> matchingTasks = taskRepo.findAll(spec, sort);

        // 4. Map to DTOs:
        return matchingTasks.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
