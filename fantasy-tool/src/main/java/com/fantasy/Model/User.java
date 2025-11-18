package com.fantasy.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id_num")  
    private long userId;

    @Column(name = "display_name")
    private String displayName;

    public User() {}    

    // TODO: update this to have team name

    public User(long userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public long getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", displayName=" + displayName + "]";
    }


}
