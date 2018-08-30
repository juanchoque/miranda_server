package com.codeformas.miranda_server.controllers;

import com.codeformas.miranda_server.model.domain.Groups;
import com.codeformas.miranda_server.services.GroupsService;
import com.codeformas.miranda_server.util.ConstantMiranda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupsController {
    private GroupsService groupsService;

    @Autowired
    public void setGroupsService(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> listGroups(@RequestBody Groups groups) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Object> response = null;

        List<Groups> listGroups = new ArrayList<Groups>();

        HashMap resHashMap = null;
        String continueHash = "";

        //ObjectMapper gson = new ObjectMapper();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        try {
            resHashMap = this.groupsService.listAll();
            continueHash = (String) resHashMap.get("CONTINUE_STATUS");
            if (continueHash.equals(ConstantMiranda.CONTINUE_STATUS_TRUE)) {
                listGroups = (List<Groups>) resHashMap.get("LIST_GROUPS");
                respuestaJson = gson.toJson(listGroups);
            } else {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            ex.printStackTrace();
        }
        response = new ResponseEntity<Object>(respuestaJson, httpStatus);

        return response;
    }

    /**
     * registro de un nuevo dato en base
     * @param groups
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method= RequestMethod.POST, produces="application/json")
    public ResponseEntity<Object> addGroups(@RequestBody Groups groups) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Object> response = null;
        HashMap resHashMap = null;
        String continueHash = "";

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        try {
            resHashMap = this.groupsService.saveOrUpdate(groups);
            continueHash = (String)resHashMap.get("CONTINUE_STATUS");
            if(continueHash.equals("TRUE")){
                respuestaJson = gson.toJson(groups, Groups.class);
            }
            else{
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }catch (Exception ex){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        response = new ResponseEntity<Object>(respuestaJson, httpStatus);

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> deleteGroups(@RequestBody Groups groups) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Object> response = null;

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        HashMap resHashMap = null;
        String continueHash = "";

        try {
            resHashMap = this.groupsService.delete(groups);
            continueHash = (String) resHashMap.get("CONTINUE_STATUS");
            if (continueHash.equals("TRUE")) {
                respuestaJson = gson.toJson(groups, Groups.class);
            } else {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        response = new ResponseEntity<Object>(respuestaJson, httpStatus);

        return response;
    }

}
