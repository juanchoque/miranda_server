package com.codeformas.miranda_server.model.domain;

public class AccountFunction {
    private Integer di;

    private Integer function;

    private Integer account;

    private String state;

    public Integer getDi() {
        return di;
    }

    public void setDi(Integer di) {
        this.di = di;
    }

    public Integer getFunction() {
        return function;
    }

    public void setFunction(Integer function) {
        this.function = function;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}