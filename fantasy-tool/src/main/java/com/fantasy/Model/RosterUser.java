package com.fantasy.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "roster_user")
public class RosterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment in DB
    @Column(name = "roster_user_id")
    private long rosterUserId;
    @Column(name = "roster_id")
    private long rosterId;
    @Column(name = "user_id_num")
    private long userId;
    @Column(name = "league_id")
    private long leagueId;

    public RosterUser() {}

    public RosterUser(long rosterUserId, long rosterId, long userId, long leagueId) {
        this.rosterUserId = rosterUserId;
        this.rosterId = rosterId;
        this.userId = userId;
        this.leagueId = leagueId;
    }

    public RosterUser(long rosterId, long userId, long leagueId) {
        this.rosterId = rosterId;
        this.userId = userId;
        this.leagueId = leagueId;
    }



    public long getRosterUserId() {
        return rosterUserId;
    }

    public void setRosterUserId(long rosterUserId) {
        this.rosterUserId = rosterUserId;
    }

    public long getRosterId() {
        return rosterId;
    }

    public void setRosterId(long rosterId) {
        this.rosterId = rosterId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public String toString() {
        return "RosterUser{" +
                "rosterUserId=" + rosterUserId +
                ", rosterId=" + rosterId +
                ", userId=" + userId +
                ", leagueId=" + leagueId +
                '}';
    }
    
}
