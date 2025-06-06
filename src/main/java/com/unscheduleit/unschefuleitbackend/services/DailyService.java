package com.unscheduleit.unschefuleitbackend.services;

import com.unscheduleit.unschefuleitbackend.dto.DailyDTO;
import com.unscheduleit.unschefuleitbackend.dto.GoalDTO;
import com.unscheduleit.unschefuleitbackend.entities.Daily;
import com.unscheduleit.unschefuleitbackend.repository.DailyRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DailyService {

    private final DailyRepo dailyRepo;

    public DailyService(DailyRepo dailyRepo) {
        this.dailyRepo = dailyRepo;
    }

    public List<DailyDTO> getAllDailies() {

        return dailyRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }

    public void deleteDaily(DailyDTO dailyDTO) {

        dailyRepo.deleteById(dailyDTO.getId());
    }


    public void updateDaily(DailyDTO dailyDTO) {

        dailyRepo.save(mapToEntity(dailyDTO));
    }

    private Daily mapToEntity(DailyDTO dto) {
        Daily daily = new Daily();

        daily.setId(dto.getId());
        daily.setTitle(dto.getTitle());
        daily.setDate(dto.getDate());
        daily.setDone(dto.isDone());
        daily.setDisabled(dto.isDisabled());
        daily.setDifficulty(dto.getDifficulty());
        daily.setTags(dto.getTags());
        daily.setCattegory(dto.getCattegory());

        return daily;
    }

    private DailyDTO mapToDto(Daily entity) {
        return new DailyDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDate(),
                entity.isDone(),
                entity.isDisabled(),
                entity.getDifficulty(),
                entity.getTags(),
                entity.getCattegory()
        );
    }




}
