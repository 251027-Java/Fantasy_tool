package com.fantasy.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "draft")
public class Draft {
    @Id
    @Column(name = "draft_id")
    private long draftId;

    @Column(name = "league_id")
    private long leagueId;

    public Draft() {}
    public Draft(long draftId, long leagueId) {
        this.draftId = draftId;
        this.leagueId = leagueId;
    }

    public long getDraftId() {
        return draftId;
    }

    public void setDraftId(long draftId) {
        this.draftId = draftId;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public String toString() {
        return "Draft{" +
                "draftId=" + draftId +
                ", leagueId=" + leagueId +
                '}';
    }
}
