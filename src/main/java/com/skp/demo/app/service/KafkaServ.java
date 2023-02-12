package com.skp.demo.app.service;

import com.skp.demo.app.model.Message;

public interface KafkaServ {
    String sendData(Message message);
}
