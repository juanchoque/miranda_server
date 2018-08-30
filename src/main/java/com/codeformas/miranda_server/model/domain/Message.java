package com.codeformas.miranda_server.model.domain;

import java.util.Date;

public class Message {
    private Integer id;

    private String message;

    private Date dateRegister;

    private String state;

    private Integer audio;

    private Integer image;

    private Integer video;

    private Integer account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getAudio() {
        return audio;
    }

    public void setAudio(Integer audio) {
        this.audio = audio;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getVideo() {
        return video;
    }

    public void setVideo(Integer video) {
        this.video = video;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }
}