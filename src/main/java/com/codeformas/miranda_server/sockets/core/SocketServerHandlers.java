package com.codeformas.miranda_server.sockets.core;

import com.codeformas.miranda_server.model.domain.Command;
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
    private SocketServerService socketServerService;

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
            InterpreterMiranda interpreterMiranda = new InterpreterMiranda();
            Command command = interpreterMiranda.decodeCommand(strMessge);
            logger.debug(">>>>>>>>>>>>>" + command) ;
            if(command != null){
                if(command.getImei() != null){
                    IoSession ioSession = StoreSocketSessions.appSessions.get(command.getImei());

                    StoreSocketSessions.appSessions.put(command.getImei(), session);

                    socketServerService.processCommand(command, session);
                }
            }

        }catch (Exception err){
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
