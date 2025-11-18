package com.fantasy.Request.RequestModels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RosterUserResponse {
    @JsonProperty("owner_id")
    private long userId;

    @JsonProperty("league_id")
    private long leagueId;

    @JsonProperty("roster_id")
    private int rosterId;

    @JsonProperty("players")
    private List<String> roster;

    @JsonProperty("starters")
    private List<String> starters;

    public RosterUserResponse() {}

    public RosterUserResponse(long userId, long leagueId, int rosterId) {
        this.userId = userId;
        this.leagueId = leagueId;
        this.rosterId = rosterId;
    }

    public long getUserId() {
        return userId;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public int getRosterId() {
        return rosterId;
    }

    public List<String> getRoster() {
        return roster;
    }

    public List<String> getStarters() {
        return starters;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public void setRosterId(int rosterId) {
        this.rosterId = rosterId;
    }

    public void setRoster(List<String> roster) {
        this.roster = roster;
    }

    public void setStarters(List<String> starters) {
        this.starters = starters;
    }

    @Override
    public String toString() {
        return "RosterUserResponse{" +
                "userId=" + userId +
                ", leagueId=" + leagueId +
                ", rosterId=" + rosterId +
                ", roster=" + roster +
                ", starters=" + starters +
                '}';
    }
}
