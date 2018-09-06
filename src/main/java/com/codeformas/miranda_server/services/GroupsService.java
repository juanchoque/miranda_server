package com.codeformas.miranda_server.services;

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

    @Autowired
    public GroupsService(GroupsRepository groupsRepository, IGroupAccountService groupAccountService) {
        this.groupsRepository = groupsRepository;
        this.groupAccountService = groupAccountService;
    }

    @Override
    public HashMap listAll() {
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
    public HashMap getById(Integer id) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        Groups groups = null;
        try {
            groups = this.groupsRepository.findOne(id);
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
    public HashMap saveOrUpdate(Groups groups) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        Groups rGroups = null;
        try {
            if(groups.getId() != null){
                rGroups = this.groupsRepository.findOne(groups.getId());
                if(rGroups != null){
                    rGroups.setName(groups.getName());
                    rGroups.setState(groups.getState());

                    //delete groupsaccoint
                    if(rGroups.getGroupAccountList() != null && groups.getGroupAccountList() != null){
                        for(int i = 0;i < rGroups.getGroupAccountList().size();i++){
                            GroupAccount rGroupAccount = rGroups.getGroupAccountList().get(i);
                            boolean found = false;
                            for(int j = 0;j < groups.getGroupAccountList().size();j++){
                                GroupAccount groupAccount = groups.getGroupAccountList().get(j);
                                if(rGroupAccount.getId() != null){
                                    if(rGroupAccount.getId().equals(groupAccount.getId())){
                                        found = true;
                                    }
                                }
                            }
                            if(!found){
                                this.groupAccountService.delete(rGroupAccount);
                            }
                        }
                    }
                }
            }
            else{
                rGroups = groups;
            }

            rGroups.getGroupAccountList().clear();
            this.groupsRepository.save(rGroups);

            for(int i = 0;i < groups.getGroupAccountList().size();i++){
                GroupAccount groupAccount = groups.getGroupAccountList().get(i);
                groupAccount.setGroups(rGroups);
                groups.getGroupAccountList().set(i, groupAccount);
                this.groupAccountService.saveOrUpdate(groupAccount);
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
    public HashMap delete(Groups groups) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";
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
