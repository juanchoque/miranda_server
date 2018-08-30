package com.codeformas.miranda_server.model.domain;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ubication")
public class Ubication implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Long id;

    @Column(name = "dateregister")
    @Expose
    private Date dateRegister = new Date();

    @Column(name = "latitude")
    @Expose
    private Double latitude;

    @Column(name = "longitude")
    @Expose
    private Double longitude;

    @Column(name = "state")
    @Expose
    private String state = "1";

    @Column(name = "acceleration")
    @Expose
    private Double acceleration = 0.0;

    @Column(name = "velocity")
    @Expose
    private Double velocity = 0.0;

    @Column(name = "account")
    @Expose
    private Integer account = 0;

    @Column(name = "imei")
    @Expose
    private String imei;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public Double getVelocity() {
        return velocity;
    }

    public void setVelocity(Double velocity) {
        this.velocity = velocity;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}