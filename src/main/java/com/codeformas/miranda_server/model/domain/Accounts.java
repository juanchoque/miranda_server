package com.codeformas.miranda_server.model.domain;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Accounts implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer id;

    @Column(name = "name")
    @Expose
    private String name;

    @Column(name = "phonenumber")
    @Expose
    private String phoneNumber;

    @Column(name = "email")
    @Expose
    private String email;

    @Column(name = "picture")
    @Expose
    private String picture;

    @Column(name = "idfather")
    @Expose
    private Long idFather;

    @Column(name = "dateregister")
    @Expose
    private Date dateRegister = new Date();

    @Column(name = "dateupdate")
    @Expose
    private Date dateUpdate = new Date();

    @Column(name = "dateend")
    @Expose
    private Date dateEnd = new Date();

    @Column(name = "state")
    @Expose
    private String state;

    @Column(name = "imei")
    @Expose
    private String imei;

    @Column(name = "admin")
    @Expose
    private Byte admin;

    @Column(name = "latitud")
    @Expose
    private Double latitud;

    @Column(name = "longitud")
    @Expose
    private Double longitud;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "accounts")
    private List<GroupAccount> groupAccountList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Long getIdFather() {
        return idFather;
    }

    public void setIdFather(Long idFather) {
        this.idFather = idFather;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Byte getAdmin() {
        return admin;
    }

    public void setAdmin(Byte admin) {
        this.admin = admin;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @XmlTransient
    public List<GroupAccount> getGroupAccountList() {
        return groupAccountList;
    }

    public void setGroupAccountList(List<GroupAccount> groupAccountList) {
        this.groupAccountList = groupAccountList;
    }

}