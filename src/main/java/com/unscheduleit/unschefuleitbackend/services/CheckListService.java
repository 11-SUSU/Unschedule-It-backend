package com.unscheduleit.unschefuleitbackend.services;

import com.unscheduleit.unschefuleitbackend.dto.CheckListItemDTO;
import com.unscheduleit.unschefuleitbackend.entities.ChecklistItem;
import com.unscheduleit.unschefuleitbackend.repository.CheckListRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CheckListService {


    private final CheckListRepo checkListRepo;

    public CheckListService(CheckListRepo checkListRepo) {
        this.checkListRepo = checkListRepo;
    }

    public void createChecklistItem(CheckListItemDTO checkListItemDTO) {

        ChecklistItem checklistItem = mapToEntity(checkListItemDTO);

        checkListRepo.save(checklistItem);
    }

    public List<CheckListItemDTO> getAllCheckListItems() {

        return checkListRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }

    public void deleteChecklistItem(String checkListItemId) {

        checkListRepo.deleteById(checkListItemId);

    }

    public void updateCheckListItem(CheckListItemDTO checkListItemDTO) {

        checkListRepo.save(mapToEntity(checkListItemDTO));

    }

    private ChecklistItem mapToEntity(CheckListItemDTO dto) {
        ChecklistItem item = new ChecklistItem();
        item.setText(dto.getText());
        item.setDone(dto.isDone());
        return item;
    }

    private CheckListItemDTO mapToDto(ChecklistItem entity) {
        return new CheckListItemDTO(
                entity.getId(),
                entity.getText(),
                entity.isDone()
        );
    }

    public ChecklistItem getCheckListItem(String checkListItemId) {

        return checkListRepo.findById(checkListItemId).get();

    }
}
