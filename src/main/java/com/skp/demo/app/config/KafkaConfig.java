package com.skp.demo.app.config;

import com.comcast.gateway.library.config.Props;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfig {

    private String topic;
    private String broker;

    public Map<String, Object> getProducerProps() {
        return Props.emptyProps()
                .withProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this::getBroker)
                .toStringObjectMap();
    }
}
