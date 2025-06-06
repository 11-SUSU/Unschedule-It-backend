package com.unscheduleit.unschefuleitbackend.controller;

import com.unscheduleit.unschefuleitbackend.dto.DailyDTO;
import com.unscheduleit.unschefuleitbackend.services.DailyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dailies")
public class DailyController {

    private final DailyService dailyService;

    public DailyController(DailyService dailyService) {
        this.dailyService = dailyService;
    }

    /**
     * Retrieve all dailies.
     * GET /api/dailies
     * Returns HTTP 200 (OK) with a JSON array of DailyDTO.
     */
    @GetMapping
    public ResponseEntity<List<DailyDTO>> getAllDailies() {
        List<DailyDTO> dailies = dailyService.getAllDailies();
        return ResponseEntity.ok(dailies);
    }

    /**
     * Update an existing daily.
     * PUT /api/dailies
     * Expects full DailyDTO (including its id). If the id does not exist, this will create a new record.
     * Returns HTTP 200 (OK) on success.
     */
    @PutMapping
    public ResponseEntity<Void> updateDaily(@RequestBody DailyDTO dailyDTO) {
        dailyService.updateDaily(dailyDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete a daily by passing a DailyDTO with at least its id populated.
     * DELETE /api/dailies
     * Returns HTTP 204 (No Content) on success.
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteDaily(@RequestBody DailyDTO dailyDTO) {
        dailyService.deleteDaily(dailyDTO);
        return ResponseEntity.noContent().build();
    }
}
