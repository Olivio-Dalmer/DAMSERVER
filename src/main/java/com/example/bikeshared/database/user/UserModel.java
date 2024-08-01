package com.example.bikeshared.database.user;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "password")
    protected String password;
    @Column(name = "nome")
    protected String nome;
    @Column(name = "sobrenome")
    protected String sobrenome;
    @Column(name = "genero")
    protected String genero;
    @Column(name = "dataNascimento")
    protected LocalDateTime dataNascimento;
    @Column(name = "BI", unique =true)
    protected String bi;
    @Column(name = "telefone")
    protected String telefone;
    @Column(name = "email", unique =true, nullable = false)
    protected String email;    
    @Column(name = "foto")
    protected String foto;
    @Column(name = "tipo")
    protected int tipo;
    @Column(name = "dataCreated")
    protected LocalDateTime dataCreated;
    @Column(name = "dataUpdated")
    protected LocalDateTime dataUpdated;




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

    /**
     *
     * @return
     *     {@link Integer }
     *
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     *     {@link Integer }
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return String
     */


    /**
     * 
     * @return
     *     {@link String }
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param value
     *
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * 
     * @return String
     *
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @param value
     *
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * 
     * @return String
     *
     *
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     *
     * @param value
     *
     */
    public void setSobrenome(String value) {
        this.sobrenome = value;
    }

    /**
     *
     * @return
     *
     */
    public String getGenero() {
        return genero;
    }

    /**
     *
     * @param value
     *
     */
    public void setGenero(String value) {
        this.genero = value;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     *
     * @return
     *
     */
    public String getBI() {
        return bi;
    }

    /**
     *
     * @param value
     *
     */
    public void setBI(String value) {
        this.bi = value;
    }

    /**
     *
     * @return
     *     {@link String }
     *
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     *
     * @param value
     *     {@link String }
     *
     */
    public void setTelefone(String value) {
        this.telefone = value;
    }

    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param value
     *
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     *
     * @return
     *     {@link String }
     *
     */
    public String getFoto() {
        return foto;
    }

    /**
     *
     * @param value
     *     {@link String }
     *
     */
    public void setFoto(String value) {
        this.foto = value;
    }

    /**
     *
     * @return
     *     {@link String }
     *
     */
    public int getTipo() {
        return tipo;
    }

    /**
     *
     * @param value
     *     {@link String }
     *
     */
    public void setTipo(int value) {
        this.tipo = value;
    }

}
