package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Ubication;
import com.codeformas.miranda_server.repository.UbicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UbicationService implements IUbicationService {
    private UbicationRepository ubicationRepository;

    @Autowired
    public UbicationService(UbicationRepository ubicationRepository) {
        this.ubicationRepository = ubicationRepository;
    }


    @Override
    public HashMap listAll() {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";

        try {
            List<Ubication> listUbications = new ArrayList<>();
            ubicationRepository.findAll().forEach(listUbications::add); //fun with Java 8
            resultMap.put("LIST_UBICATIONS", listUbications);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);

        return resultMap;
    }

    @Override
    public HashMap getById(Long id) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";

        Ubication ubication = null;
        try {
            ubication = ubicationRepository.findOne(id);
            resultMap.put("UBICATION", ubication);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);

        return resultMap;
    }

    @Override
    public HashMap saveOrUpdate(Ubication ubication) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";
        try {
            ubicationRepository.save(ubication);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);
        return resultMap;
    }

    @Override
    public HashMap delete(Ubication ubication) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";
        try {
            ubicationRepository.delete(ubication);
        }catch (Exception er){
            continueMap = "FALSE";
        }

        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);
        return resultMap;
    }

}
