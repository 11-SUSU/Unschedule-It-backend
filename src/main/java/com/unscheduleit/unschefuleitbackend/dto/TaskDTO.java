package com.unscheduleit.unschefuleitbackend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {

    private String id;

    private String title;

    private Instant date;

    private boolean done;

    private boolean disabled;

    private String notes;

    private String difficulty;

    private String repeats;

    private String repeatEvery;

    private String tags;

    private Instant timeDone;

    private String goalId;


}
