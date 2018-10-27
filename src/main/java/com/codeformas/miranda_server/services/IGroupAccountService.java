package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.GroupAccount;
import java.util.HashMap;

public interface IGroupAccountService extends ICrudService {

    HashMap findByImei(String imei);

}
