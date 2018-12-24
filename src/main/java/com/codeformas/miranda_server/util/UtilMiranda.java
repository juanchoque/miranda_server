package com.codeformas.miranda_server.util;

import com.codeformas.miranda_server.model.domain.MessageError;
import com.codeformas.miranda_server.sockets.observer.Subject;
import org.springframework.http.HttpStatus;

import static com.codeformas.miranda_server.util.StoreSocketSessions.appSessions;

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
