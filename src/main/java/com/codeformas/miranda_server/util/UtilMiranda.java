package com.codeformas.miranda_server.util;

import com.codeformas.miranda_server.model.domain.MessageError;
import org.springframework.http.HttpStatus;

public final class UtilMiranda {
    private static UtilMiranda utilMiranda;

    private UtilMiranda() {

    }

    public static UtilMiranda getInstance(){
        if(utilMiranda == null){
            utilMiranda = new UtilMiranda();
        }
        return utilMiranda;
    }

    public MessageError getFormatMessage(String messageHash, HttpStatus httpStatus){
        MessageError messages = null;

        messages = new MessageError();
        messages.setStatus(httpStatus.value());
        messages.setMessage(messageHash);

        return messages;
    }
}
