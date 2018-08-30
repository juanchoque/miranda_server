package com.codeformas.miranda_server.model.domain;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "group_accounts")
public class GroupAccount  implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer id;

    @Column(name = "state")
    @Expose
    private Byte state;

    /*@Column(name = "groups")
    private Integer groups;

    @Column(name = "accounts")
    private Integer accounts;*/

    @JoinColumn(name = "accounts", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Accounts accounts;

    @JoinColumn(name = "groups", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Groups groups;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}