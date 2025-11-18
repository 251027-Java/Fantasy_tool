package com.fantasy.Model;

import jakarta.persistence.*;

@Embeddable
public class RosterIdObj {
    
    @Column(name = "roster_user_id")
    private long rosterUserId;

    @Column(name = "player_id")
    private String playerId;

    @Column(name = "week_num")
    private int weekNum;

    public RosterIdObj() {}
    public RosterIdObj(long rosterUserId, String playerId, int weekNum) {
        this.rosterUserId = rosterUserId;
        this.playerId = playerId;
        this.weekNum = weekNum;
    }

    public long getRosterUserId() {
        return rosterUserId;
    }
    public void setRosterUserId(long rosterUserId) {
        this.rosterUserId = rosterUserId;
    }
    public String getPlayerId() {
        return playerId;
    }
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
    public int getWeekNum() {
        return weekNum;
    }
    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public String toString() {
        return "RosterIdObj{" +
                "rosterUserId=" + rosterUserId +
                ", playerId='" + playerId + '\'' +
                ", weekNum=" + weekNum +
                '}';
    }


}
