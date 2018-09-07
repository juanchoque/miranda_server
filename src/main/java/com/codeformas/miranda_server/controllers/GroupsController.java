package com.codeformas.miranda_server.controllers;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.model.domain.Groups;
import com.codeformas.miranda_server.model.domain.MessageError;
import com.codeformas.miranda_server.services.GroupsService;
import com.codeformas.miranda_server.util.ConstantMiranda;
import com.codeformas.miranda_server.util.UtilMiranda;
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
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = ConstantMiranda.APP_JSON)
    public ResponseEntity<Object> listGroups(@RequestBody Groups groups) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Object> response = null;

        List<Groups> listGroups = new ArrayList<Groups>();

        HashMap resHashMap = null;
        boolean continueHash = false;
        String messageHash = "";
        UtilMiranda utilMiranda = new UtilMiranda();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        try {
            resHashMap = this.groupsService.listAll();
            continueHash = (boolean) resHashMap.get(ConstantMiranda.STATUS);
            if (continueHash) {
                listGroups = (List<Groups>) resHashMap.get(ConstantMiranda.OBJECT);
                respuestaJson = gson.toJson(listGroups);
            } else {
                messageHash = (String)resHashMap.get(ConstantMiranda.MESSAGE);
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                MessageError messages = utilMiranda.getFormatMessage(messageHash, httpStatus);
                respuestaJson = gson.toJson(messages);
            }

        } catch (Exception ex) {
            messageHash = ex.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            MessageError messages = utilMiranda.getFormatMessage(messageHash, httpStatus);
            respuestaJson = gson.toJson(messages);
        }
        response = new ResponseEntity<Object>(respuestaJson, httpStatus);

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/listbyaccount", method = RequestMethod.POST, produces = ConstantMiranda.APP_JSON)
    public ResponseEntity<Object> listGroupsByAccount(@RequestBody Accounts accounts) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Object> response = null;

        List<Groups> listGroups = new ArrayList<Groups>();

        HashMap resHashMap = null;
        boolean continueHash = false;
        String messageHash = "";
        UtilMiranda utilMiranda = new UtilMiranda();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        try {
            resHashMap = this.groupsService.listAllByAcount(accounts);
            continueHash = (boolean) resHashMap.get(ConstantMiranda.STATUS);
            if (continueHash) {
                listGroups = (List<Groups>) resHashMap.get(ConstantMiranda.OBJECT);
                respuestaJson = gson.toJson(listGroups);
            } else {
                messageHash = (String)resHashMap.get(ConstantMiranda.MESSAGE);
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                MessageError messages = utilMiranda.getFormatMessage(messageHash, httpStatus);
                respuestaJson = gson.toJson(messages);
            }

        } catch (Exception ex) {
            messageHash = ex.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            MessageError messages = utilMiranda.getFormatMessage(messageHash, httpStatus);
            respuestaJson = gson.toJson(messages);
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
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = ConstantMiranda.APP_JSON)
    public ResponseEntity<Object> addGroups(@RequestBody Groups groups) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Object> response = null;
        HashMap resHashMap = null;
        boolean continueHash = false;
        String messageHash = "";
        UtilMiranda utilMiranda = new UtilMiranda();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        try {
            resHashMap = this.groupsService.saveOrUpdate(groups);
            continueHash = (boolean)resHashMap.get(ConstantMiranda.STATUS);
            if(continueHash){
                respuestaJson = gson.toJson(groups, Groups.class);
            }
            else{
                messageHash = (String)resHashMap.get(ConstantMiranda.MESSAGE);
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                MessageError messages = utilMiranda.getFormatMessage(messageHash, httpStatus);
                respuestaJson = gson.toJson(messages);
            }
        }catch (Exception ex){
            messageHash = ex.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            MessageError messages = utilMiranda.getFormatMessage(messageHash, httpStatus);
            respuestaJson = gson.toJson(messages);
        }
        response = new ResponseEntity<Object>(respuestaJson, httpStatus);

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = ConstantMiranda.APP_JSON)
    public ResponseEntity<Object> deleteGroups(@RequestBody Groups groups) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Object> response = null;

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        HashMap resHashMap = null;
        boolean continueHash = false;
        String messageHash = "";
        UtilMiranda utilMiranda = new UtilMiranda();

        try {
            resHashMap = this.groupsService.delete(groups);
            continueHash = (boolean) resHashMap.get(ConstantMiranda.STATUS);
            if (continueHash) {
                respuestaJson = gson.toJson(groups, Groups.class);
            }
            else {
                messageHash = (String)resHashMap.get(ConstantMiranda.MESSAGE);
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                MessageError messages = utilMiranda.getFormatMessage(messageHash, httpStatus);
                respuestaJson = gson.toJson(messages);
            }
        } catch (Exception ex) {
            messageHash = ex.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            MessageError messages = utilMiranda.getFormatMessage(messageHash, httpStatus);
            respuestaJson = gson.toJson(messages);
        }
        response = new ResponseEntity<Object>(respuestaJson, httpStatus);

        return response;
    }

}
