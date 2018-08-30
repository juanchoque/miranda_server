
package com.codeformas.miranda_server.sockets.core;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SocketClientHandlers extends IoHandlerAdapter {

    private boolean response = false;

    private static final Logger logger = LoggerFactory.getLogger(SocketClientHandlers.class);

    public SocketClientHandlers(){
        response = false;
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        logger.debug("[CLIENT] Caught : " + session);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info("[CLIENT] START ");
        logger.info("[CLIENT] MESSAGE " + message.toString());

        try {

        }catch (Exception ex){
            logger.info("[CLIENT] ERROR" + ex.getStackTrace());
        }
        logger.info("[CLIENT] END ");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.debug("[CLIENT] IDLE : " + session.getIdleCount( status ));
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.debug("[CLIENT] Sent : " + session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.debug("[CLIENT] Closed : " + session);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logger.debug("[CLIENT] Created : " + session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.debug("[CLIENT] Opened : " + session);
    }

    public boolean isResponse() {
        return response;
    }

}
