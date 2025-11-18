package com.fantasy.Request.RequestModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NFLStateResponse {
    @JsonProperty("week")
    private String week;

    @JsonProperty("season")
    private String season;

    @JsonProperty("display_week")
    private String displayWeek;

    public NFLStateResponse() {
    }

    public NFLStateResponse(String week, String season, String displayWeek) {
        this.week = week;
        this.season = season;
        this.displayWeek = displayWeek;
    }

    public String getWeek() {
        return week;
    }

    public String getSeason() {
        return season;
    }

    public String getDisplayWeek() {
        return displayWeek;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setDisplayWeek(String displayWeek) {
        this.displayWeek = displayWeek;
    }

    @Override
    public String toString() {
        return "NFLStateResponse{" +
                "week='" + week + '\'' +
                ", season='" + season + '\'' +
                ", displayWeek='" + displayWeek + '\'' +
                '}';
    }
}
