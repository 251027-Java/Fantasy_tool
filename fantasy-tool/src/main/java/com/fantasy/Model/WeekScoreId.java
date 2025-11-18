package com.fantasy.Model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@Embeddable
public class WeekScoreId implements Serializable {
    @Column(name = "roster_user_id")
    private Long rosterUserId;
    @Column(name = "week_num")
    private Integer weekNum;

    public WeekScoreId() {}
    public WeekScoreId(Long rosterUserId, int weekNum) {
        this.rosterUserId = rosterUserId;
        this.weekNum = weekNum;
    }

    public Long getRosterUserId() {
        return rosterUserId;
    }

    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public void setRosterUserId(Long rosterUserId) {
        this.rosterUserId = rosterUserId;
    }

    @Override
    public String toString() {
        return "WeekScoreId{" +
                "rosterUserId='" + rosterUserId + '\'' +
                ", weekNum=" + weekNum +
                '}';
    }

    public boolean equals(Object o) {
        if (o instanceof WeekScoreId weekScoreId) {
            return this.rosterUserId.equals(weekScoreId.rosterUserId) 
                && this.weekNum.equals(weekScoreId.weekNum);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.rosterUserId, this.weekNum);
    }
}