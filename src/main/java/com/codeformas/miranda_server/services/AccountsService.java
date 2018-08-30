package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AccountsService implements IAccountsService {
    private AccountsRepository accountsRepository;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }


    @Override
    public HashMap listAll() {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";

        try {
            List<Accounts> listAccounts = new ArrayList<>();
            accountsRepository.findAll().forEach(listAccounts::add); //fun with Java 8
            resultMap.put("LIST_ACCOUNTS", listAccounts);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);

        return resultMap;
    }

    @Override
    public HashMap getById(Integer id) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";

        Accounts accounts = null;
        try {
            accounts = accountsRepository.findOne(id);
            resultMap.put("ACCOUNTS", accounts);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);

        return resultMap;
    }

    @Override
    public HashMap saveOrUpdate(Accounts accounts) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";
        try {
            accountsRepository.save(accounts);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);
        return resultMap;
    }

    @Override
    public HashMap delete(Accounts accounts) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";
        try {
            accountsRepository.delete(accounts);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);
        return resultMap;
    }

}
