package com.example.bikeshared.database.user.gestor;


import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

import java.io.Serializable;

@Entity
@Table(name = "gestors")
public class GestorModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "idEstacao")
    private Long idEstacao;
    @Column(name = "idUser")
    private Long idUser;
    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdEstacao() {
        return idEstacao;
    }
    public void setIdEstacao(Long idEstacao) {
        this.idEstacao = idEstacao;
    }
}
