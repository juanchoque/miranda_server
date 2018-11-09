package com.codeformas.miranda_server.controllers;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.model.domain.MessageError;
import com.codeformas.miranda_server.services.AccountsService;
import com.codeformas.miranda_server.util.ConstantMiranda;
import com.codeformas.miranda_server.util.UtilMiranda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Api(value = ConstantMiranda.VALUE_ACCOUNTS_CONTROLLER, description = ConstantMiranda.DESC_ACCOUNTS_CONTROLLER)
public class AccountsController {
    private AccountsService accountsService;

    @Autowired
    public void setAccountsService(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @ResponseBody
    @ApiOperation(value = ConstantMiranda.DESC_LIST_ACCOUNTS)
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = ConstantMiranda.APP_JSON)
    public ResponseEntity<Object> listAccounts(@RequestBody Accounts accounts) {
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Object> response = null;
        HashMap resHashMap = null;

        List<Accounts> listAccounts = new ArrayList<Accounts>();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();


        boolean continueHash = false;

        String messageHash = "";
        String respuestaJson = "";

        UtilMiranda utilMiranda = UtilMiranda.getInstance();

        try {
            resHashMap = this.accountsService.list(accounts);
            continueHash = (boolean) resHashMap.get(ConstantMiranda.STATUS);
            if (continueHash) {
                listAccounts = (List<Accounts>) resHashMap.get(ConstantMiranda.OBJECT);
                respuestaJson = gson.toJson(listAccounts);
            }
            else {
                messageHash = (String)resHashMap.get(ConstantMiranda.MESSAGE);
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                MessageError messages = utilMiranda.getFormatMessage(messageHash, httpStatus);
                respuestaJson = gson.toJson(messages);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
     * @param accounts
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method= RequestMethod.POST, produces=ConstantMiranda.APP_JSON)
    public ResponseEntity<Object> addAccounts(@RequestBody Accounts accounts) {
        String messageHash = "";
        String respuestaJson = "";

        boolean continueHash = false;

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Object> response = null;
        Gson gson = new GsonBuilder()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        HashMap resHashMap = null;
        UtilMiranda utilMiranda = UtilMiranda.getInstance();

        try {
            resHashMap = this.accountsService.saveUpdate(accounts);
            continueHash = (boolean)resHashMap.get(ConstantMiranda.STATUS);
            if(continueHash){
                respuestaJson = gson.toJson(accounts, Accounts.class);
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
    public ResponseEntity<Object> deleteAccounts(@RequestBody Accounts accounts) {
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Object> response = null;

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        HashMap resHashMap = null;

        boolean continueHash = false;

        String messageHash = "";
        String respuestaJson = "";

        UtilMiranda utilMiranda = UtilMiranda.getInstance();

        try {
            resHashMap = this.accountsService.delete(accounts);
            continueHash = (boolean) resHashMap.get(ConstantMiranda.STATUS);
            if (continueHash) {
                respuestaJson = gson.toJson(accounts, Accounts.class);
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

}
