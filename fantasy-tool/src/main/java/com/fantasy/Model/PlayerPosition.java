package com.fantasy.Model;

import jakarta.persistence.*;


@Entity
@Table(name= "player_position")
public class PlayerPosition {

    @EmbeddedId
    private PlayerPositionId id;

    public PlayerPosition() {}

    public PlayerPosition(PlayerPositionId id) {
        this.id = id;
    }

    public PlayerPositionId getId() { return id; }
    public void setId(PlayerPositionId id) { this.id = id; }

    @Override
    public boolean equals(Object o ) {
        if (o instanceof PlayerPosition playerPosition) {
            return this.id.equals(playerPosition.id);
        }
        return false;
    }


    
}
