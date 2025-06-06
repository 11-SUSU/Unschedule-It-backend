package com.unscheduleit.unschefuleitbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class DailyDTO {

    private String id;

    private String title;

    private Instant date;

    private boolean done;

    private boolean disabled;

    private String difficulty;

    private String tags;

    private String cattegory;


}
