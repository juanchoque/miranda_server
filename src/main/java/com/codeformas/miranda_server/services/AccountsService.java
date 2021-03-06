package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.repository.AccountsRepository;
import com.codeformas.miranda_server.util.ConstantMiranda;
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
    public HashMap list(Object o) {
        HashMap resultMap = new HashMap();

        boolean status = false;

        String messageTemp = "";

        try {
            List<Accounts> listAccounts = new ArrayList<>();
            this.accountsRepository.findAll().forEach(listAccounts::add); //fun with Java 8
            if(listAccounts != null){
                resultMap.put(ConstantMiranda.OBJECT, listAccounts);
                status = true;
            }
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage().toLowerCase();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

    @Override
    public HashMap getById(Object o) {
        HashMap resultMap = new HashMap();

        boolean status = false;

        String messageTemp = "";

        Accounts accounts = null;
        try {
            accounts = this.accountsRepository.findOne(((Accounts)o).getId());
            if(accounts != null){
                resultMap.put(ConstantMiranda.OBJECT, accounts);
                status = true;
            }
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage().toLowerCase();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

    @Override
    public HashMap saveUpdate(Object o) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";
        try {
            this.accountsRepository.save((Accounts) o);
            status = true;
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage().toLowerCase();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);
        return resultMap;
    }

    @Override
    public HashMap delete(Object o) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";
        try {
            this.accountsRepository.delete((Accounts) o);
            status = true;
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage().toString();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);
        return resultMap;
    }
}
