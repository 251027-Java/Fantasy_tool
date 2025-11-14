package com.fantasy.Request.RequestModels;

import java.util.List;

public class PlayersResponse {
    private String playerId;
    private List<String> fantasyPositions;
    private String team;
    private String firstName;
    private String lastName;
    private Integer rotoworldId;
    private String statsId;
    private Integer fantasyDataId;

    // Getters and setters
    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }

    public List<String> getFantasyPositions() { return fantasyPositions; }
    public void setFantasyPositions(List<String> fantasyPositions) { this.fantasyPositions = fantasyPositions; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Integer getRotoworldId() { return rotoworldId; }
    public void setRotoworldId(Integer rotoworldId) { this.rotoworldId = rotoworldId; }

    public String getStatsId() { return statsId; }
    public void setStatsId(String statsId) { this.statsId = statsId; }

    public Integer getFantasyDataId() { return fantasyDataId; }
    public void setFantasyDataId(Integer fantasyDataId) { this.fantasyDataId = fantasyDataId; }

    @Override
    public String toString() {
        return String.format("Player{id=%s, team=%s, name=%s %s, pos=%s}",
                playerId, team, firstName, lastName, fantasyPositions);
    }
}
