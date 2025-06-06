package com.unscheduleit.unschefuleitbackend.services;

import com.unscheduleit.unschefuleitbackend.dto.GoalDTO;
import com.unscheduleit.unschefuleitbackend.entities.Goal;
import com.unscheduleit.unschefuleitbackend.repository.GoalRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {


    private final GoalRepo goalRepo;

    public GoalService(GoalRepo goalRepo) {
        this.goalRepo = goalRepo;
    }

    public void createGoal(GoalDTO goalDTO) {

        Goal entity = mapToEntity(goalDTO);
        goalRepo.save(entity);

    }

    public List<GoalDTO> getAllGoals() {

        return goalRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        
    }


    public void deleteGoal(GoalDTO goalDTO) {

        goalRepo.deleteById(goalDTO.getId());
    }


    public void updateGoal(GoalDTO goalDTO) {

        goalRepo.save(mapToEntity(goalDTO));
    }

    private Goal mapToEntity(GoalDTO dto) {
        Goal goal = new Goal();

        goal.setId(dto.getId());
        goal.setTitle(dto.getTitle());
        goal.setDescription(dto.getDescription());
        goal.setDate(dto.getDate());
        goal.setDone(dto.isDone());
        goal.setDisabled(dto.isDisabled());
        goal.setDifficulty(dto.getDifficulty());
        goal.setTasks(dto.getTasks());

        return goal;
    }

    private GoalDTO mapToDto(Goal entity) {
        return new GoalDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDate(),
                entity.isDone(),
                entity.isDisabled(),
                entity.getDifficulty(),
                entity.getTasks()
        );
    }

}
