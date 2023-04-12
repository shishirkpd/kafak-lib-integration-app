package com.skp.demo.app.service.impl;

import com.comcast.gateway.library.KafkaProducerFactory;
import com.comcast.gateway.library.PayloadWithMetaInfo;
import com.skp.demo.app.service.KafkaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KfImpl implements KafkaServ {

    @Autowired
    KafkaProducerFactory<String, String> sink;

    @Autowired
    KafkaProducerFactory<String, Object> avroSink;

    @Override
    public String sendData(PayloadWithMetaInfo payload) {
        avroSink.publish(payload.getLastUpdateEventId(), payload);
        return "Success";
    }
}
