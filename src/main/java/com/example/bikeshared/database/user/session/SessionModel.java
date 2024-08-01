package com.example.bikeshared.database.user.session;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

import java.io.Serializable;

@Entity
@Table(name = "session")
public class SessionModel implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "token", unique =true, nullable = false)
    protected String token;
    @Column(name = "userId", unique =true, nullable = false)
    private Long userId;
    @Column(name = "dataCreated")
    protected LocalDateTime dataCreated;
    @Column(name = "dataUpdated")
    protected LocalDateTime dataUpdated;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public LocalDateTime getDataCreated() {
        return dataCreated;
    }
    public void setDataCreated(LocalDateTime dataCreated) {
        this.dataCreated = dataCreated;
    }
    public LocalDateTime getDataUpdated() {
        return dataUpdated;
    }
    public void setDataUpdated(LocalDateTime dataUpdated) {
        this.dataUpdated = dataUpdated;
    }

}
