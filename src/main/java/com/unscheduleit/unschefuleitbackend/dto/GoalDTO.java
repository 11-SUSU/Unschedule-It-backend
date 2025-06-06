package com.unscheduleit.unschefuleitbackend.dto;


import com.unscheduleit.unschefuleitbackend.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GoalDTO {

    private String id;

    private String title;

    private String description;

    private Instant date;

    private boolean done;

    private boolean disabled;

    private String difficulty;

    private List<Task> tasks;


}
