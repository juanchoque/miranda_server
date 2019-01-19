package com.codeformas.miranda_server.sockets.core;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.codeformas.miranda_server.model.domain.Command;
import com.codeformas.miranda_server.model.domain.Ubication;
import com.codeformas.miranda_server.services.UbicationService;
import com.codeformas.miranda_server.sockets.observer.Subject;
import com.codeformas.miranda_server.sockets.observer.TrackerObserver;
import com.codeformas.miranda_server.sockets.services.SocketServerService;
import com.codeformas.miranda_server.util.InterpreterMiranda;
import com.codeformas.miranda_server.util.StoreSocketSessions;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketServerHandlers extends IoHandlerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(SocketServerHandlers.class);

    @Autowired
    private Subject subject;

    @Autowired
    private InterpreterMiranda interpreterMiranda;

    @Autowired
    private UbicationService ubicationService;

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logger.debug("[SER  VER] SESSION CREATED: " + session.getId());
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.debug("[SERVER] SESSION OPENED: " + session.getId());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info(String.format("[SERVER] START"));

        String strMessge = message.toString();
        logger.info(String.format("[SERVER] MESSAGE RECEIVED" + strMessge));

        try {
            Command command = this.interpreterMiranda.decodeCommand(strMessge);
            if(command != null & command.getImei() != null){

                strMessge = this.interpreterMiranda.encodeCommand(command);

                TrackerObserver trackerObserver = new TrackerObserver(this.subject);
                trackerObserver.setImei(command.getImei());
                trackerObserver.setIoSession(session);
                trackerObserver.add();//save session and data

                //save in data base
                /*try {
                    Ubication ubication = new Ubication();
                    ubication.setLongitude(command.getLongitude());
                    ubication.setLatitude(command.getLatitude());
                    ubication.setImei(command.getImei());
                    this.ubicationService.saveUpdate(ubication);
                }catch (Exception ex){
                }*/
                //end save

                this.subject.notifyAllObservers(command.getImei(), strMessge);
            }
        }catch (Exception err){
            err.printStackTrace();
            logger.info("[SERVER] ERROR" + err.getStackTrace());
        }

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.debug("[SERVER] SESSION CLOSED:" + session.getId());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.info(String.format("[SERVER] sessionIdle ,status",session.getId(), status.toString()));
        super.sessionIdle(session, status);
    }


    @Override
    public void messageSent(IoSession session, Object message) {
        logger.info(String.format("[SERVER] session send ,status",session.getId()));
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        logger.info(String.format("[SERVER] exceptionCaught ,status",session.getId(), session.getId()));
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
        logger.info(String.format("[SERVER] inputClosed ,status",session.getId()));
    }

}
