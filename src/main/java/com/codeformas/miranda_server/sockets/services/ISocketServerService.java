package com.codeformas.miranda_server.sockets.services;

import com.codeformas.miranda_server.model.domain.Command;
import com.codeformas.miranda_server.model.domain.Ubication;
import org.apache.mina.core.session.IoSession;

import java.util.HashMap;

public interface ISocketServerService {
    HashMap processCommand(Command command, IoSession session);
}
