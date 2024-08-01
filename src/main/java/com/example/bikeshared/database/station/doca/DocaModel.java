package com.example.bikeshared.database.station.doca;


import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.io.Serializable;

@Entity
@Table(name = "docas")
public class DocaModel implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "reference")
    protected String reference;
    @Column(name = "aberta")
    protected boolean aberta;
    @Column(name = "idEstacao")
    private Long idEstacao;
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
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public boolean isAberta() {
        return aberta;
    }
    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }
    public Long getIdEstacao() {
        return idEstacao;
    }
    public void setIdEstacao(Long idEstacao) {
        this.idEstacao = idEstacao;
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

