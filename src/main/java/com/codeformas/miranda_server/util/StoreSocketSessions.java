package com.codeformas.miranda_server.util;

import org.apache.mina.core.session.IoSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StoreSocketSessions {
    public static Map<String, IoSession> appSessions =  Collections.synchronizedMap(new HashMap<String, IoSession>());
}
