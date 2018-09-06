package com.codeformas.miranda_server.util;

import com.codeformas.miranda_server.model.domain.MessageError;
import org.springframework.http.HttpStatus;

public class UtilMiranda {
    public MessageError getFormatMessage(String messageHash, HttpStatus httpStatus){
        MessageError messages = null;

        messages = new MessageError();
        messages.setStatus(httpStatus.value());
        messages.setMessage(messageHash);

        return messages;
    }
}
