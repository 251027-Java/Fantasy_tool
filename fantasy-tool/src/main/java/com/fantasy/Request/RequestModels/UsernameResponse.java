package com.fantasy.Request.RequestModels;

import com.fasterxml.jackson.annotation.JsonProperty;

// class to get the useriD from username
public class UsernameResponse {

    @JsonProperty("user_id")
    private long user_id;



    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
