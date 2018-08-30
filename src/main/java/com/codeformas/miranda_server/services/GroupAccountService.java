package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.GroupAccount;
import com.codeformas.miranda_server.repository.GroupAccountRepository;
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
        String continueMap = "TRUE";
        String messageTemp = "";

        try {
            List<GroupAccount> listGroupAccounts = new ArrayList<>();
            groupAccountRepository.findAll().forEach(listGroupAccounts::add); //fun with Java 8
            resultMap.put("LIST_GROUP_ACCOUNTS", listGroupAccounts);
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

        GroupAccount groupAccount = null;
        try {
            groupAccount = groupAccountRepository.findOne(id);
            resultMap.put("GROUP_ACCOUNT", groupAccount);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);

        return resultMap;
    }

    @Override
    public HashMap saveOrUpdate(GroupAccount groupAccount) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
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
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);
        return resultMap;
    }

    @Override
    public HashMap delete(GroupAccount groupAccount) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";
        try {
            groupAccountRepository.delete(groupAccount);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);
        return resultMap;
    }

    @Override
    public HashMap findByImei(String imei) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";

        List<GroupAccount>listGroupsAccounts = new ArrayList<>();
        try {
            listGroupsAccounts = groupAccountRepository.findByImei(imei);
            if(listGroupsAccounts == null){
                listGroupsAccounts = new ArrayList<>();
            }
            resultMap.put("LIST_GROUP_ACCOUNTS", listGroupsAccounts);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);
        return resultMap;
    }

}
