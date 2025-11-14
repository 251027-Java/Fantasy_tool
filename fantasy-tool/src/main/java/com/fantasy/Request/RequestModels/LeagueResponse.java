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

    public void setNumTeams(int total_rosters) {
        this.numTeams = total_rosters;
    }

    public int getSeason() {
        return seasonYear;
    }

    public void setSeason(int season) {
        this.seasonYear = season;
    }

    public long getLeague_id() {
        return leagueId;
    }

    public void setLeague_id(long league_id) {
        this.leagueId = league_id;
    }

    public long getDraft_id() {
        return draftId;
    }

    public void setDraft_id(long draft_id) {
        this.draftId = draft_id;
    }


}
