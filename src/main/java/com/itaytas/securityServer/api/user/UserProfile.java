package com.itaytas.securityServer.api.user;

import java.util.Date;

public class UserProfile {
    private String id;
    private String username;
    private String name;
    private Date joinedAt;

    public UserProfile(String id, String username, String name, Date joinedAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.joinedAt = joinedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }
}
