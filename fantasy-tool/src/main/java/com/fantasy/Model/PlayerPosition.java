package com.fantasy.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "player_positions")
public class PlayerPosition {

    @Column(name = "player_id") 
    private String playerId;

    @Column(name = "position")
    private String position;

    public PlayerPosition() {}
    public PlayerPosition(String playerId, String position) {
        this.playerId = playerId;
        this.position = position;
    }

    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    
}
