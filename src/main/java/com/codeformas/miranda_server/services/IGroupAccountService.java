package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.GroupAccount;
import java.util.HashMap;

public interface IGroupAccountService {
    HashMap listAll();

    HashMap getById(Integer id);

    HashMap saveOrUpdate(GroupAccount groupAccount);

    HashMap delete(GroupAccount groupAccount);

    HashMap findByImei(String imei);

}
