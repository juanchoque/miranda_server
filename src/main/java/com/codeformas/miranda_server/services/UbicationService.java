package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Ubication;
import com.codeformas.miranda_server.repository.UbicationRepository;
import com.codeformas.miranda_server.util.ConstantMiranda;
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
        boolean status = false;
        String messageTemp = "";

        try {
            List<Ubication> listUbications = new ArrayList<>();
            this.ubicationRepository.findAll().forEach(listUbications::add); //fun with Java 8
            if(listUbications != null){
                resultMap.put(ConstantMiranda.OBJECT, listUbications);
                status = true;
            }
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

    @Override
    public HashMap getById(Long id) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";

        Ubication ubication = null;
        try {
            ubication = this.ubicationRepository.findOne(id);
            if(ubication != null){
                resultMap.put(ConstantMiranda.OBJECT, ubication);
                status = true;
            }
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

    @Override
    public HashMap saveOrUpdate(Ubication ubication) {
        HashMap resultMap = new HashMap();
        boolean status = false;
        String messageTemp = "";
        try {
            this.ubicationRepository.save(ubication);
            status = true;
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

    @Override
    public HashMap delete(Ubication ubication) {
        HashMap resultMap = new HashMap();
        boolean status = true;
        String messageTemp = "";
        try {
            this.ubicationRepository.delete(ubication);
            status = true;
        }catch (Exception er){
            status = false;
            messageTemp = er.getMessage();
        }

        resultMap.put(ConstantMiranda.STATUS, status);
        resultMap.put(ConstantMiranda.MESSAGE, messageTemp);

        return resultMap;
    }

}
