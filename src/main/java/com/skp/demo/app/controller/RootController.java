package com.skp.demo.app.controller;

import com.comcast.gateway.library.PayloadWithMetaInfo;
import com.skp.demo.app.service.KafkaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.skp.demo.app")
@RequestMapping("api/v1/")
public class RootController {
    @Autowired
    KafkaServ kafkaServ;

    @PostMapping("send")
    public ResponseEntity<String> send(@RequestBody PayloadWithMetaInfo payload) {
        return new ResponseEntity(kafkaServ.sendData(payload), HttpStatus.OK);
    }
}
