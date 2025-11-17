package com.fantasy.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "league")
public class League {

    @Id
    @Column(name = "league_id")
    private long leagueId;
    @Column(name = "num_rosters")
    private int numRosters;
    @Column(name = "league_name", length = 50)
    private String leagueName;
    @Column(name = "season")
    private int seasonYear;

    public League() {}

    public League(long leagueId, int numRosters, String leagueName, int seasonYear) {
        this.leagueId = leagueId;
        this.numRosters = numRosters;
        this.leagueName = leagueName;
        this.seasonYear = seasonYear;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public int getNumRosters() {
        return numRosters;
    }

    public void setNumRosters(int numRosters) {
        this.numRosters = numRosters;
    }

    public String getLeagueName() {
        return this.leagueName;
    }

    public void setLeagueName(String name) {
        this.leagueName = name;
    }

    public int getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }


    @Override
    public String toString() {
        return "League{" +
                "leagueId=" + leagueId +
                ", numRosters=" + numRosters +
                ", name='" + leagueName + '\'' +
                ", season=" + seasonYear +
                '}';
    }
    
}
