package com.example.bikeshared.database.user.client;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.Serializable;

@Entity
@Table(name = "clients")
public class ClientModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "saldo")
    protected double saldo;
    @Column(name = "idUser")
    private Long idUser;
    @Column(name = "devedor")
    private boolean devedor;

    public boolean isDevedor() {
        return devedor;
    }
    public void setDevedor(boolean devedor) {
        this.devedor = devedor;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
