package com.unscheduleit.unschefuleitbackend.controller;

import com.unscheduleit.unschefuleitbackend.dto.GoalDTO;
import com.unscheduleit.unschefuleitbackend.services.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    /**
     * Create a new Goal.
     * Expects a JSON body containing all fields of GoalDTO except id (which will be generated).
     * Returns HTTP 201 (Created) on success.
     */
    @PostMapping
    public ResponseEntity<Void> createGoal(@RequestBody GoalDTO goalDTO) {
        goalService.createGoal(goalDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Retrieve all goals.
     * Returns a list of GoalDTO objects and HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<GoalDTO>> getAllGoals() {
        List<GoalDTO> goals = goalService.getAllGoals();
        return ResponseEntity.ok(goals);
    }

    /**
     * Update an existing Goal.
     * Expects the full GoalDTO (including its id). If the id does not exist, service.save(...) will create a new record.
     * Returns HTTP 200 (OK) on success.
     */
    @PutMapping
    public ResponseEntity<Void> updateGoal(@RequestBody GoalDTO goalDTO) {
        goalService.updateGoal(goalDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete a Goal.
     * Expects a JSON body with at least the 'id' field populated. Returns HTTP 204 (No Content) on success.
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteGoal(@RequestBody GoalDTO goalDTO) {
        goalService.deleteGoal(goalDTO);
        return ResponseEntity.noContent().build();
    }
}
