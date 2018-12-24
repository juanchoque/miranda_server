package com.codeformas.miranda_server.sockets.observer;

import com.codeformas.miranda_server.model.domain.Command;
import org.apache.mina.core.session.IoSession;

public abstract class Observer {
    protected Subject subject;
    private String imei;
    private IoSession ioSession;
    public abstract void emitData(String message);

    public IoSession getIoSession() {
        return ioSession;
    }

    public void setIoSession(IoSession ioSession) {
        this.ioSession = ioSession;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
