package com.codeformas.miranda_server.controllers;

import com.codeformas.miranda_server.model.domain.Command;
import com.codeformas.miranda_server.sockets.services.SocketClientService;
import com.codeformas.miranda_server.util.ConstantMiranda;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commands")
public class CommandsController {

    @ResponseBody
    @RequestMapping(value = "/sendcommand", method = RequestMethod.POST, produces = ConstantMiranda.APP_JSON)
    public ResponseEntity<Object> listUbications(@RequestBody Command command) {
        String respuestaJson = "";
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Object> response = null;

        try {

            SocketClientService socketService = new SocketClientService();
            socketService.sendCommands(command);

        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            ex.printStackTrace();
        }
        response = new ResponseEntity<Object>(respuestaJson, httpStatus);

        return response;
    }

}
