package com.fantasy.Request.RequestModels;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchupResponse {
    
    @JsonProperty("matchup_id")
    private Integer matchupId;

    @JsonProperty("roster_id")
    private Integer rosterId;

    @JsonProperty("starters")
    private List<String> starters;

    @JsonProperty("players_points")
    private Map<String, Double> playersPoints;

    @JsonProperty("points")
    private Double points;

    public MatchupResponse() {}

    public MatchupResponse(Integer matchupId, Integer rosterId, List<String> starters, Map<String, Double> playersPoints, Double points) {
        this.matchupId = matchupId;
        this.rosterId = rosterId;
        this.starters = starters;
        this.playersPoints = playersPoints;
        this.points = points;
    }

    public Integer getMatchupId() {
        return matchupId;
    }

    public void setMatchupId(Integer matchupId) {
        this.matchupId = matchupId;
    }

    public Integer getRosterId() {
        return rosterId;
    }

    public void setRosterId(Integer rosterId) {
        this.rosterId = rosterId;
    }

    public List<String> getStarters() {
        return starters;
    }

    public void setStarters(List<String> starters) {
        this.starters = starters;
    }

    public Map<String, Double> getPlayersPoints() {
        return playersPoints;
    }

    public void setPlayersPoints(Map<String, Double> playersPoints) {
        this.playersPoints = playersPoints;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "MatchupResponse{" +
                "matchupId=" + matchupId +
                ", rosterId=" + rosterId +
                ", starters=" + starters +
                ", playersPoints=" + playersPoints +
                ", points=" + points +
                '}';
    }
}
