package com.codeformas.miranda_server.services;

import java.util.HashMap;

public interface IGroupAccountService extends ICrudService {

    HashMap findByImei(String imei);

}
