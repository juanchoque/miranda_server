package com.codeformas.miranda_server.sockets.observer;

import com.codeformas.miranda_server.model.domain.Command;
import com.codeformas.miranda_server.util.StoreSocketSessions;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.codeformas.miranda_server.util.StoreSocketSessions.appSessions;

@Component
public class Subject {
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * method for add data
     * @param observer
     */
    public void attach(Observer observer){
        StoreSocketSessions.appSessions.put(observer.getImei(), observer);
    }

    /**
     * method for get data
     * @param message
     */
    public void notifyAllObservers(String imei, String message){
        for(Map.Entry<String, Observer> entry : appSessions.entrySet()) {
            Observer observer = entry.getValue();
            if(!observer.getImei().equals(imei)){
                observer.emitData(message);
            }
        }
    }
}
