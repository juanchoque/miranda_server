package com.codeformas.miranda_server.websockets.core;

import com.codeformas.miranda_server.util.StoreSocketSessions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class WebSocketServerHandlers extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerHandlers.class);

//    private WebSocketServerService webSocketServerService;
//
//    public WebSocketServerHandlers(WebSocketServerServiceImpl webSocketServerServiceImpl) {
//        this.webSocketServerService = webSocketServerServiceImpl;
//    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)throws InterruptedException, IOException {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try{
//            if(this.webSocketServerService != null){
//                //this.webSocketServerService.processServerMessage(message, session);
//            }
        }catch (Exception ex){

        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/*Gson gson = new Gson();
		try {
			//GprsDataObjectBean gprsDataObjectBean = gson.fromJson(value.get("data").toString(), GprsDataObjectBean.class);
			GprsDataObjectBean gprsDataObjectBean = new GprsDataObjectBean();
			gprsDataObjectBean.setImei("862951024258221");
			gprsDataObjectBean.setCommandCode("G00");
			gprsDataObjectBean.setContent(value.get("data"));

			SocketClientService socketClient = new SocketClientServiceImpl();
			HashMap resHashMap = socketClient.sendBasicCommand(gprsDataObjectBean);
		}
		catch (Exception ex){

		}*/

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
//        logger.debug("=OPEN WEB SESSION=>" + session.getId() + " <C/S> " + StoreSocketSessions.webSessions.size());
//        System.out.println("=OPEN WEB SESSION=>" + session.getId() + " <C/S> " + StoreSocketSessions.webSessions.size());
//
//        System.out.println("=0000000000 COMO ESTA EL OBJETO 00000000>" + webSocketServerService);
//
//        System.out.println(session.getHandshakeHeaders().getFirst("host"));
//        System.out.println(session.getHandshakeHeaders().getFirst("Authorization"));
//
//        StoreSocketSessions.webSessions.add(session);

        //save web session  we need id account for save session
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus){
        logger.debug("=CLOSE WEB SESSION=>" + session.getId());
        System.out.println("=CLOSE WEB SESSION=>" + session.getId());
//        for(int i = 0; i < StoreSocketSessions.webSessions.size();i++) {
//            WebSocketSession webSocketSession = StoreSocketSessions.webSessions.get(i);
//            if(webSocketSession.getId() == session.getId()){
//                StoreSocketSessions.webSessions.remove(i);
//                --i;
//            }
//        }
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}
