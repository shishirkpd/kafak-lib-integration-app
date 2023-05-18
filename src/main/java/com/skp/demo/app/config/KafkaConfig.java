package com.skp.demo.app.config;

import com.comcast.gateway.library.config.Props;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfig {

    private String topic;
    private String bootstrapServers;
    private String schemaRegistryUrl;

    public Map<String, Object> getProducerProps() {
        return Props.emptyProps()
                .withProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this::getBootstrapServers)
                .toStringObjectMap();
    }


    // Serializer related config need to provided
    public Map<String, Object> getProducerPropsAvro() {
        return Props.emptyProps()
                .withProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, () -> StringSerializer.class.getName())
                .withProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, () -> KafkaAvroSerializer.class.getName())
                .withProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, () -> this.getBootstrapServers())
                .toStringObjectMap();
    }
}
