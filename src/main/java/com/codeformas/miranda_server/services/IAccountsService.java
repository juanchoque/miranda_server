package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Accounts;
import java.util.HashMap;

public interface IAccountsService {
    HashMap listAll();

    HashMap getById(Integer id);

    HashMap saveOrUpdate(Accounts accounts);

    HashMap delete(Accounts accounts);

}
