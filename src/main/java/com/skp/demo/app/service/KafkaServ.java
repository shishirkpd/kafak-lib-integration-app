package com.skp.demo.app.service;

import com.comcast.gateway.library.PayloadWithMetaInfo;

public interface KafkaServ {
    String sendData(PayloadWithMetaInfo payload);
}
