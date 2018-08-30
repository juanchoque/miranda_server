package com.codeformas.miranda_server.model.domain;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "groups")
public class Groups  implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer id;

    @Column(name = "name")
    @Expose
    private String name;

    @Column(name = "datecreate")
    @Expose
    private Date dateCreate = new Date();

    @Column(name = "dateupdate")
    @Expose
    private Date dateUpdate = new Date();

    @Column(name = "state")
    @Expose
    private Byte state;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "groups")
    @Expose
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @XmlTransient
    public List<GroupAccount> getGroupAccountList() {
        return groupAccountList;
    }

    public void setGroupAccountList(List<GroupAccount> groupAccountList) {
        this.groupAccountList = groupAccountList;
    }
}