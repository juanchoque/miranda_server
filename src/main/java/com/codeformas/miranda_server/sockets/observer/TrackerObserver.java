package com.codeformas.miranda_server.sockets.observer;

public class TrackerObserver extends Observer {

    public TrackerObserver(Subject subject) {
        this.subject = subject;
        //this.subject.attach(this);
    }

    @Override
    public void emitData(String message) {
        try {
            this.getIoSession().write(message);
        }catch (Exception ex){
        }
    }

    public void add(){
        this.subject.attach(this);
    }

}
