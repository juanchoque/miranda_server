package com.codeformas.miranda_server.sockets.services;

import com.codeformas.miranda_server.model.domain.Command;
import com.codeformas.miranda_server.sockets.core.SocketClientHandlers;
import com.codeformas.miranda_server.util.ConstantMiranda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;

public class SocketClientService implements ISocketClientService {
    private IoConnector connector;
    private SocketClientHandlers socketClientHandler;

    private static IoSession session;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SocketClientService.class);

    /**
     * Create the TCPClient's instance
     */
    public SocketClientService() {
        connector = new NioSocketConnector();
        socketClientHandler   = new SocketClientHandlers();

        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter(new TextLineCodecFactory( Charset.forName( "UTF-8" ))));

        connector.setHandler(socketClientHandler);
        SocketSessionConfig dcfg = (SocketSessionConfig) connector.getSessionConfig();

        ConnectFuture connFuture = connector.connect(new InetSocketAddress(ConstantMiranda.IP_MINA, ConstantMiranda.MINA_PORT));
        connFuture.awaitUninterruptibly();
        session = connFuture.getSession();

    }

    public HashMap sendCommands(Command command){
        logger.info("[>.. write first ");
        logger.info("[>.. session object " + session);

        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";

        String result = "";

        Gson gson = new GsonBuilder()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        try {
            result = gson.toJson(command, Command.class);

            session.write(result);

            int contadorEspera = 0;
            while (!socketClientHandler.isResponse()) {
                try {
                    Thread.sleep(1);
                    if(contadorEspera > 12){//wait eight seconds for response
                        break;
                    }
                    contadorEspera++;
                } catch (InterruptedException ex) {

                }
            }
            logger.info("[>.. later haldler client " + socketClientHandler.isResponse());

        }catch (Exception e){
            messageTemp = e.getStackTrace().toString();
            continueMap = "FALSE";
            e.printStackTrace();
        }
        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);

        // Delete session...
        connector.dispose(true);
        logger.info("[>.. END SOCKET CLIENT"+ " CONTINUE: "+ continueMap);

        return resultMap;
    }

}
