package com.codeformas.miranda_server.model.domain;

public class MessageError {
    private int status;
    private String message;

    public MessageError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
