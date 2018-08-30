package com.codeformas.miranda_server.sockets.services;

import com.codeformas.miranda_server.model.domain.Command;
import com.codeformas.miranda_server.model.domain.GroupAccount;
import com.codeformas.miranda_server.model.domain.Ubication;
import com.codeformas.miranda_server.services.GroupAccountService;
import com.codeformas.miranda_server.services.UbicationService;
import com.codeformas.miranda_server.util.ConstantMiranda;
import com.codeformas.miranda_server.util.StoreSocketSessions;
import com.google.gson.Gson;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class SocketServerService implements ISocketServerService {

    @Autowired
    private UbicationService ubicationService;

    @Autowired
    private GroupAccountService groupAccountService;

    @Override
    public HashMap processCommand(Command command, IoSession session) {
        HashMap resultMap = new HashMap();
        String continueMap = "TRUE";
        String messageTemp = "";

        Gson gson = new Gson();
        String result = "";

        List<GroupAccount>listGroupAccounts = new ArrayList<>();

        try {
            if(command.getCmd() != null){
                System.out.println(".............................<>" + command.getCmd());
                switch (command.getCmd()){
                    case ConstantMiranda.CMD_UBIC:
                        System.out.println("-------------CMD_UBIC-------------->");
                        /*resultMap = this.groupAccountService.findByImei(command.getImei());
                        continueMap = (String)resultMap.get("CONTINUE_STATUS");
                        if(continueMap.equals(ConstantMiranda.CONTINUE_STATUS_TRUE)){
                            listGroupAccounts = (List<GroupAccount>) resultMap.get("LIST_GROUP_ACCOUNTS");

                            //emit for al groups
                            for(int i  = 0;i < listGroupAccounts.size();i++){
                                GroupAccount groupAccount = listGroupAccounts.get(i);
                                if(groupAccount.getAccounts().getImei() != null){
                                    if(!groupAccount.getAccounts().getImei().equals(command.getImei())){
                                        IoSession rIoSession = StoreSocketSessions.appSessions.get(groupAccount.getAccounts().getImei());
                                        if(rIoSession != null){
                                            Command rCommand = new Command();
                                            rCommand.setCmd(ConstantMiranda.CMD_UBIC);
                                            rCommand.setLatitude(command.getLatitude());
                                            rCommand.setLongitude(command.getLatitude());

                                            result = gson.toJson(rCommand);
                                            rIoSession.write(result);
                                        }
                                    }
                                }
                            }
                        }*/

                        //register in db
                        Ubication ubication = new Ubication();
                        ubication.setLongitude(command.getLongitude());
                        ubication.setLatitude(command.getLatitude());
                        ubication.setImei(command.getImei());

                        //ubicationService.saveOrUpdate(ubication);

                        break;
                    case ConstantMiranda.CMD_SMS:
                        IoSession ioSession = StoreSocketSessions.appSessions.get(command.getImeiDest());
                        System.out.println("-------------CMD_SMS-------------->" + ioSession);
                        if(ioSession != null){
                            result = gson.toJson(command);
                            ioSession.write(result);
                        }
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            messageTemp = e.getStackTrace().toString();
            continueMap = "FALSE";
            e.printStackTrace();
        }
        resultMap.put("CONTINUE_STATUS", continueMap);
        resultMap.put("MESSAGE", messageTemp);

        return resultMap;
    }

}
