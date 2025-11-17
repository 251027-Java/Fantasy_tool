package com.fantasy.Request.RequestModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
    
    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("display_name")
    private String displayName;

    public UserResponse() {}    

    // TODO: update this to have team name

    public UserResponse(long userId, String displayName) {
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
        return "UserResponse [userId=" + userId + ", displayName=" + displayName + "]";
    }
}
