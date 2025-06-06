package com.unscheduleit.unschefuleitbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private String id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Instant date;

    private boolean done;

    private boolean disabled;

    private String difficulty;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "goal_id")
    private List<Task> tasks = new ArrayList<>();

    // -- constructors, getters & setters --

    public Goal() {}

    // getters & setters omitted for brevity
}

