package com.skp.demo.app.service.impl;

import com.comcast.gateway.library.service.impl.KafkaProducerFactory;
import com.skp.demo.app.model.Message;
import com.skp.demo.app.service.KafkaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KfImpl implements KafkaServ {

    @Autowired
    KafkaProducerFactory<String, String> sink;

    @Override
    public String sendData(Message message) {
        sink.publish(message.getKey(), message.getValue());
        return "Success";
    }
}
