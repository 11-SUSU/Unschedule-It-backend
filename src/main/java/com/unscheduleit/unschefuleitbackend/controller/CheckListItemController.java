package com.unscheduleit.unschefuleitbackend.controller;

import com.unscheduleit.unschefuleitbackend.dto.CheckListItemDTO;
import com.unscheduleit.unschefuleitbackend.services.CheckListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checklist")
public class CheckListItemController {

    private final CheckListService checkListService;

    public CheckListItemController(CheckListService checkListService) {
        this.checkListService = checkListService;
    }

    /**
     * GET /api/checklist
     */
    @GetMapping
    public List<CheckListItemDTO> getAll() {
        return checkListService.getAllCheckListItems();
    }

    /**
     * POST /api/checklist
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CheckListItemDTO dto) {
        checkListService.createChecklistItem(dto);
    }

    /**
     * PUT /api/checklist/{id}
     */
    @PutMapping("/{id}")
    public void update(@RequestBody CheckListItemDTO dto) {

        checkListService.updateCheckListItem(dto);
    }

    /**
     * DELETE /api/checklist/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {

        checkListService.deleteChecklistItem(id);
    }
}
