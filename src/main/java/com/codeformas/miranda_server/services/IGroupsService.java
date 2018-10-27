package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.model.domain.Groups;

import java.util.HashMap;

public interface IGroupsService extends ICrudService{
    HashMap listAllByAcount(Accounts accounts);
}
