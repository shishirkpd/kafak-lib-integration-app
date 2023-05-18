package com.skp.demo.app.service.impl;

import com.comcast.gateway.library.KafkaProducerFactory;
import com.comcast.gateway.library.PayloadWithMetaInfo;
import com.comcast.gateway.library.config.KafkaProducerConfig;
import com.comcast.gateway.library.service.KafkaProducerService;
import com.skp.demo.app.service.KafkaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

@Service
public class KfImpl implements KafkaServ {

    @Autowired
    KafkaProducerFactory<String, String> sink;

//    @Autowired
//    KafkaProducerFactory<String, Object> avroSink;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplateAvro;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @Override
    public String sendData(PayloadWithMetaInfo payload) {
        //avroSink.publish(payload.getLastUpdateEventId(), payload);
       // KafkaTemplate<String, Object> kafkaTemplateAvro = new KafkaProducerConfig().kafkaTemplateAvro();

        //kafkaTemplateAvro.send(payload.getLastUpdateEventId(), payload);

        kafkaProducerService.sendMessage(payload);
        return "Success";
    }
}
