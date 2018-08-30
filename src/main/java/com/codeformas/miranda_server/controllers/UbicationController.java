package com.codeformas.miranda_server.controllers;

import com.codeformas.miranda_server.model.domain.Ubication;
import com.codeformas.miranda_server.services.UbicationService;
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
@RequestMapping("/ubications")
public class UbicationController {
    private UbicationService ubicationService;

    @Autowired
    public void setUbicationService(UbicationService ubicationService) {
        this.ubicationService = ubicationService;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> listUbications(@RequestBody Ubication ubication) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Object> response = null;

        List<Ubication> listUbications = new ArrayList<Ubication>();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                .create();

        HashMap resHashMap = null;
        String continueHash = "";
        try {
            resHashMap = this.ubicationService.listAll();
            continueHash = (String) resHashMap.get("CONTINUE_STATUS");
            if (continueHash.equals(ConstantMiranda.CONTINUE_STATUS_TRUE)) {
                listUbications = (List<Ubication>) resHashMap.get("LIST_UBICATIONS");
                respuestaJson = gson.toJson(listUbications);
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
     * @param ubication
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method= RequestMethod.POST, produces="application/json")
    public ResponseEntity<Object> addUbication(@RequestBody Ubication ubication) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Object> response = null;
        Gson gson = new GsonBuilder().setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS).create();
        HashMap resHashMap = null;
        String continueHash = "";

        try {
            resHashMap = ubicationService.saveOrUpdate(ubication);
            continueHash = (String)resHashMap.get("CONTINUE_STATUS");
            if(continueHash.equals("TRUE")){
                respuestaJson = gson.toJson(ubication, Ubication.class);
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
    public ResponseEntity<Object> deleteUbications(@RequestBody Ubication ubication) {
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
            resHashMap = ubicationService.delete(ubication);
            continueHash = (String) resHashMap.get("CONTINUE_STATUS");
            if (continueHash.equals("TRUE")) {
                respuestaJson = gson.toJson(ubication, Ubication.class);
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
