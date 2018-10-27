package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.model.domain.GroupAccount;
import com.codeformas.miranda_server.model.domain.Groups;
import com.codeformas.miranda_server.repository.GroupsRepository;
import com.codeformas.miranda_server.util.ConstantMiranda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GroupsService implements IGroupsService {
    private GroupsRepository groupsRepository;

    private IGroupAccountService groupAccountService;

    private Groups rGroups = null;

    private boolean found;

    @Autowired
    public GroupsService(GroupsRepository groupsRepository, IGroupAccountService groupAccountService) {
        this.groupsRepository = groupsRepository;
        this.groupAccountService = groupAccountService;
    }


    @Override
    public HashMap listAllByAcount(Accounts accounts) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        try {
            List<GroupAccount> listGroups = new ArrayList<>();
            this.groupsRepository.findAllByAccount(accounts.getId()).forEach(listGroups::add); //fun with Java 8
            if(listGroups != null){
                resultMap.put(ConstantMiranda.OBJECT, listGroups);
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

    @Override
    public HashMap list(Object o) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        try {
            List<Groups> listGroups = new ArrayList<>();
            this.groupsRepository.findAll().forEach(listGroups::add); //fun with Java 8
            if(listGroups != null){
                resultMap.put(ConstantMiranda.OBJECT, listGroups);
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

    @Override
    public HashMap getById(Object o) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        Groups groups = null;
        try {
            groups = this.groupsRepository.findOne(((Groups)o).getId());
            if(groups != null){
                resultMap.put(ConstantMiranda.OBJECT, groups);
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
    public HashMap saveUpdate(Object o) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        Groups groups = (Groups) o;
        this.rGroups = null;
        try {
            if(groups.getId() != null){
                this.rGroups = this.groupsRepository.findOne(groups.getId());
                if(this.rGroups != null){
                    this.rGroups.setName(groups.getName());
                    this.rGroups.setState(groups.getState());

                    //delete groupsaccoint
                    if(this.rGroups.getGroupAccountList() != null && groups.getGroupAccountList() != null){
                        rGroups.getGroupAccountList().forEach(x ->{
                            found = false;
                            groups.getGroupAccountList().forEach(y ->{
                                if(x.getId() != null){
                                    if(x.getId().equals(y.getId())){
                                        found = true;
                                        return;
                                    }
                                }
                            });
                            if(!found){
                                this.groupAccountService.delete(x);
                            }
                        });
                    }

                }
            }
            else{
                this.rGroups = groups;
            }

            rGroups.getGroupAccountList().clear();
            this.groupsRepository.save(this.rGroups);

            if(groups.getGroupAccountList() != null){
                groups.getGroupAccountList().forEach(x ->{
                    x.setGroups(rGroups);
                    this.groupAccountService.saveUpdate(x);
                });
            }

            resultMap.put(ConstantMiranda.OBJECT, rGroups);
            status = true;

        }catch (Exception er){
            status = true;
            messageTemp  = er.getMessage().toString();
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

        Groups groups = (Groups) o;
        try {
            this.groupsRepository.delete(groups);
            resultMap.put(ConstantMiranda.OBJECT, groups);
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
