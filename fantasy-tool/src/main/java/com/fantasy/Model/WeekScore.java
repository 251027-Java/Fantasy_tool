package com.fantasy.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "week_score")
public class WeekScore {

    @EmbeddedId
    private WeekScoreId id;

    @Column(name = "score")
    private Double score;

    
    public WeekScore() {}
    
    public WeekScore(WeekScoreId id, Double score) {
        this.id = id;
        this.score = score;
    }

    public WeekScoreId getId() {
        return id;
    }

    public void setId(WeekScoreId id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public WeekScoreId getWeekScoreId() {
        return this.id;
    }

    public void setWeekScoreId(WeekScoreId weekScoreId) {
        this.id = weekScoreId;
    }

    
}
