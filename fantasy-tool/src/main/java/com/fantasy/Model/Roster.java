package com.fantasy.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "roster")
public class Roster {
    @EmbeddedId
    private RosterIdObj id;

    private Boolean isStarting;

    private Integer points;

    public Roster() {}
    public Roster(RosterIdObj id) {
        this.id = id;
    }
    
}
