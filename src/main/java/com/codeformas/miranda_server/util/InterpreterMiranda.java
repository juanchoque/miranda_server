package com.codeformas.miranda_server.util;

import com.codeformas.miranda_server.model.domain.Command;
import com.codeformas.miranda_server.model.domain.Ubication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InterpreterMiranda {
    public Command decodeCommand(String code){
        Command command = null;

        try {
            if(code != null){
                Gson gson = new GsonBuilder()
                        .setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS)
                        .create();
                command = gson.fromJson(code, Command.class);
                command.setDateTime(new Date());
            }
        }catch (Exception err){
            err.printStackTrace();
        }

        return command;
    }

    public String encodeCommand(Command command) {
        String result = "";
        try {
            Gson gson = new GsonBuilder().setDateFormat(ConstantMiranda.FORMAT_DATE_YYYY_MM_DD_HH_MM_SS).create();
            result = gson.toJson(command);
        }catch (Exception error){

        }
        return result;
    }
}
