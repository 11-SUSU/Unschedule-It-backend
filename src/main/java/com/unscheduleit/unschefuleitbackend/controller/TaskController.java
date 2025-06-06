package com.unscheduleit.unschefuleitbackend.controller;

import com.unscheduleit.unschefuleitbackend.dto.TaskCheckListDTO;
import com.unscheduleit.unschefuleitbackend.dto.TaskDTO;
import com.unscheduleit.unschefuleitbackend.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * GET /api/tasks
     */
    @GetMapping
    public List<TaskDTO> getAll(
            @RequestParam(required = false) String goalId,
            @RequestParam(required = false) String difficulty,
            /**
             * frontend is sending “tags_like” for each tag. If there are multiple tags,
             * they will appear multiple times:
             *   GET /api/tasks?tags_like=work&tags_like=urgent
             */
            @RequestParam(name = "tags_like", required = false) List<String> tags,
            /**
             * frontend is sending “_sort” and “_order” for sorting:
             *   GET /api/tasks?_sort=date&_order=asc
             *   GET /api/tasks?_sort=difficulty&_order=desc
             */
            @RequestParam(name = "_sort", required = false) String sortBy,
            @RequestParam(name = "_order", defaultValue = "asc") String order
    ) {
        return taskService.getTasksFilteredAndSorted(goalId, difficulty, tags, sortBy, order);
    }


    /**
     * POST /api/tasks
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TaskDTO dto) {
        taskService.createTask(dto);
    }

    /**
     * PUT /api/tasks/{id}
     */
    @PutMapping("/{id}")
    public void update(@PathVariable String id,
                       @RequestBody TaskDTO dto) {
        dto.setId(id);
        taskService.updateTask(dto);
    }

    /**
     * DELETE /api/tasks/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/checkListItem")
    @ResponseStatus(HttpStatus.OK)
    public void checkListItem(@RequestBody TaskCheckListDTO dto) {

        taskService.addCheckListItem(dto);

    }
}
