package com.askrindo.aossubrogasi.entity.aos;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name = "t_token")
public class T_Token extends BaseEnity<String>{

    @Id
    @Column(name="id",unique = true)
    private String id = UUID.randomUUID().toString();
    @Column(name="token",length = 100000)
    private String token;
    @Column(name="token_expired")
    private Integer tokenExpired;
    @Column(name="is_active")
    private Integer isActive;
    @Column(name="id_user",length = 60)
    private String  idUser;
    
    
    public Integer getTokenExpired() {
        return tokenExpired;
    }
    public void setTokenExpired(Integer tokenExpired) {
        this.tokenExpired = tokenExpired;
    }
    public Integer getIsActive() {
        return isActive;
    }
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    
}
