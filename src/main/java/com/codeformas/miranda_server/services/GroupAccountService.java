package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.GroupAccount;
import com.codeformas.miranda_server.repository.GroupAccountRepository;
import com.codeformas.miranda_server.util.ConstantMiranda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GroupAccountService implements IGroupAccountService {
    private GroupAccountRepository groupAccountRepository;

    @Autowired
    public GroupAccountService(GroupAccountRepository groupAccountRepository) {
        this.groupAccountRepository = groupAccountRepository;
    }


    @Override
    public HashMap listAll() {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        try {
            List<GroupAccount> listGroupAccounts = new ArrayList<>();
            this.groupAccountRepository.findAll().forEach(listGroupAccounts::add); //fun with Java 8
            if(listGroupAccounts != null){
                resultMap.put(ConstantMiranda.OBJECT, listGroupAccounts);
                status = true;
            }
        }catch (Exception er){
            status = false;
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

    @Override
    public HashMap getById(Integer id) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        GroupAccount groupAccount = null;
        try {
            groupAccount = groupAccountRepository.findOne(id);
            if(groupAccount != null){
                resultMap.put(ConstantMiranda.OBJECT, groupAccount);
                status = true;
            }
        }catch (Exception er){
            messageTemp = er.getMessage().toString();
            status = false;
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

    @Override
    public HashMap saveOrUpdate(GroupAccount groupAccount) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        GroupAccount rGroupAccount = null;
        try {
            rGroupAccount = this.groupAccountRepository.getByAccountGroup(groupAccount.getAccounts().getId(), groupAccount.getGroups().getId());
            if(rGroupAccount != null){
                rGroupAccount.setGroups(groupAccount.getGroups());
                rGroupAccount.setState(groupAccount.getState());
                rGroupAccount.setAccounts(groupAccount.getAccounts());
            }
            else{
                rGroupAccount = groupAccount;
            }
            this.groupAccountRepository.save(rGroupAccount);

            resultMap.put(ConstantMiranda.OBJECT, rGroupAccount);
            status = true;
        }catch (Exception er){
            status = false;
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);
        return resultMap;
    }

    @Override
    public HashMap delete(GroupAccount groupAccount) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";
        try {
            this.groupAccountRepository.delete(groupAccount);
            status = true;
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage().toString();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

    @Override
    public HashMap findByImei(String imei) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        List<GroupAccount>listGroupsAccounts = new ArrayList<>();
        try {
            listGroupsAccounts = groupAccountRepository.findByImei(imei);
            if(listGroupsAccounts != null){
                resultMap.put(ConstantMiranda.OBJECT, listGroupsAccounts);
                status = true;
            }
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage().toString();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

}
