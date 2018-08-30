package com.codeformas.miranda_server.controllers;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.services.AccountsService;
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
@RequestMapping("/accounts")
public class AccountsController {
    private AccountsService accountsService;

    @Autowired
    public void setAccountsService(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> listAccounts(@RequestBody Accounts accounts) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Object> response = null;

        List<Accounts> listAccounts = new ArrayList<Accounts>();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        HashMap resHashMap = null;
        String continueHash = "";
        try {
            resHashMap = this.accountsService.listAll();
            continueHash = (String) resHashMap.get("CONTINUE_STATUS");
            if (continueHash.equals(ConstantMiranda.CONTINUE_STATUS_TRUE)) {
                listAccounts = (List<Accounts>) resHashMap.get("LIST_ACCOUNTS");
                respuestaJson = gson.toJson(listAccounts);
            } else {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
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
    @RequestMapping(value="/add", method= RequestMethod.POST, produces="application/json")
    public ResponseEntity<Object> addAccounts(@RequestBody Accounts accounts) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Object> response = null;
        Gson gson = new GsonBuilder().setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS).create();
        HashMap resHashMap = null;
        String continueHash = "";

        try {
            resHashMap = this.accountsService.saveOrUpdate(accounts);
            continueHash = (String)resHashMap.get("CONTINUE_STATUS");
            if(continueHash.equals("TRUE")){
                respuestaJson = gson.toJson(accounts, Accounts.class);
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
    public ResponseEntity<Object> deleteAccounts(@RequestBody Accounts accounts) {
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
            resHashMap = this.accountsService.delete(accounts);
            continueHash = (String) resHashMap.get("CONTINUE_STATUS");
            if (continueHash.equals("TRUE")) {
                respuestaJson = gson.toJson(accounts, Accounts.class);
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
