package com.unscheduleit.unschefuleitbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "dailies")
public class Daily {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private String id;

    private String title;

    private Instant date;

    private boolean done;

    private boolean disabled;

    private String difficulty;

    private String tags;

    private String cattegory;


    public Daily() {}


}
