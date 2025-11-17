package com.fantasy.Request.RequestModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeagueResponse {
    @JsonProperty("total_rosters")
    private int numTeams;
    @JsonProperty("season")
    private int seasonYear;

    @JsonProperty("league_id")
    private long leagueId;

    @JsonProperty("draft_id")
    private long draftId;

    @JsonProperty("name")
    private String name;


    public int getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    }

    public int getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public long getDraftId() {
        return draftId;
    }

    public void setDraftId(long draftId) {
        this.draftId = draftId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LeagueResponse{" +
                "numTeams=" + numTeams +
                ", seasonYear=" + seasonYear +
                ", leagueId=" + leagueId +
                ", draftId=" + draftId +
                ", name='" + name + '\'' +
                '}';
    }


}
