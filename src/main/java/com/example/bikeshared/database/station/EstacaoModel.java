package com.example.bikeshared.database.station;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.io.Serializable;

@Entity
@Table(name = "estacao")
public class EstacaoModel implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", unique =true, nullable = false)
    protected String nome;
    @Column(name = "url")
    protected String url;
    @Column(name = "dataCreated")
    protected LocalDateTime dataCreated;
    @Column(name = "dataUpdated")
    protected LocalDateTime dataUpdated;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    //
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
