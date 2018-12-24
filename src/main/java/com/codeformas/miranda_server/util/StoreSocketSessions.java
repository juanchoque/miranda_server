package com.codeformas.miranda_server.util;

import com.codeformas.miranda_server.sockets.observer.Observer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StoreSocketSessions {
    public static Map<String, Observer> appSessions =  Collections.synchronizedMap(new HashMap<String, Observer>());
}
