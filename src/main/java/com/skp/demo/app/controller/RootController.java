package com.skp.demo.app.controller;

import com.skp.demo.app.model.Message;
import com.skp.demo.app.service.KafkaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan("com.skp.demo.app")
@RequestMapping("api/v1/")
public class RootController {
    @Autowired
    KafkaServ kafkaServ;

    @PostMapping("send")
    public ResponseEntity<String> send(@RequestBody Message message) {
        return new ResponseEntity(kafkaServ.sendData(message), HttpStatus.OK);
    }
}
