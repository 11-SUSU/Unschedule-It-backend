package com.unscheduleit.unschefuleitbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CheckListItemDTO {

    private String id;

    private String text;

    private boolean done;
}
