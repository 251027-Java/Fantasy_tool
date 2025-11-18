package com.fantasy.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "player")
public class Player {
    
    @Id
    @Column(name = "player_id")
    private String playerId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "team")
    private String team;

    @Column(name = "fantasy_data_id")
    private Integer fantasyDataId;

    @Column(name = "stats_id")
    private String statsId;

    @Column(name = "roto_world_id")
    private Integer rotoworldId;


    public Player() {}

    public Player(String playerId, String fullName, String team, Integer fantasyDataId, String statsId, Integer rotoworldId) {
        this.playerId = playerId;
        this.fullName = fullName;
        this.team = team;
        this.fantasyDataId = fantasyDataId;
        this.statsId = statsId;
        this.rotoworldId = rotoworldId;
    }

    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public Integer getFantasyDataId() { return fantasyDataId; }
    public void setFantasyDataId(Integer fantasyDataId) { this.fantasyDataId = fantasyDataId; }
    public String getStatsId() { return statsId; }
    public void setStatsId(String statsId) { this.statsId = statsId; }
    public Integer getRotoworldId() { return rotoworldId; }
    public void setRotoworldId(Integer rotoworldId) { this.rotoworldId = rotoworldId; }

}
