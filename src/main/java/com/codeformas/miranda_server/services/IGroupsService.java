package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.model.domain.Groups;

import java.util.HashMap;

public interface IGroupsService {
    HashMap listAll();

    HashMap getById(Integer id);

    HashMap saveOrUpdate(Groups groups);

    HashMap delete(Groups groups);

    HashMap listAllByAcount(Accounts accounts);
}
