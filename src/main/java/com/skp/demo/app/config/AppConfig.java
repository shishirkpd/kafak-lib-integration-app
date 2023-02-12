package com.skp.demo.app.config;

import com.comcast.gateway.library.service.impl.KafkaProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    KafkaConfig kafkaConfig(){
        return new KafkaConfig();
    }

    @Bean
    KafkaProducerFactory<String, String> sink(KafkaConfig kafkaConfig){
        return KafkaProducerFactory.newStringBuilder()
                .withTopic(kafkaConfig.getTopic())
                .withProperties(kafkaConfig.getProducerProps())
                .build();
    }
}
