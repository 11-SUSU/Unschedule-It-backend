package com.unscheduleit.unschefuleitbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
public class ChecklistItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private String id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private boolean done;

    public ChecklistItem() {
    }

    public ChecklistItem(String text, boolean done) {
        this.text = text;
        this.done = done;
    }

    // getters & setters
}

