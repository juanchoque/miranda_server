package com.codeformas.miranda_server.model.domain;

public class Functions {
    private Integer id;

    private String name;

    private String description;

    private String state;

    private String functioncol;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getFunctioncol() {
        return functioncol;
    }

    public void setFunctioncol(String functioncol) {
        this.functioncol = functioncol == null ? null : functioncol.trim();
    }
}