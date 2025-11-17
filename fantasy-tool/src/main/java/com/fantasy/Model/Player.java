package com.fantasy.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "player")
public class Player {
    
    @Column(name = "player_id")
    private String playerId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "team")
    private String team;

    @Column(name = "fantasy_data_id")
    private String fantasyDataId;

    @Column(name = "stats_id")
    private String statsId;

    @Column(name = "roto_world_id")
    private String rotoworldId;


    public Player() {}

}
