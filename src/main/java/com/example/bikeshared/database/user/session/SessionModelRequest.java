package com.example.bikeshared.database.user.session;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class SessionModelRequest {
    String token;
    Long userId;

    public SessionModelRequest(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
