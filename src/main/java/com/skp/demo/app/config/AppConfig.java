package com.skp.demo.app.config;

import com.comcast.gateway.library.KafkaProducerFactory;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    KafkaConfig kafkaConfig() {
        return new KafkaConfig();
    }

    @Bean
    KafkaProducerFactory<String, String> sink(KafkaConfig kafkaConfig) {
        return KafkaProducerFactory.newStringBuilder()
                .withTopic(kafkaConfig.getTopic())
                .withProperties(kafkaConfig.getProducerProps())
                .build();
    }

    // This is needed when we want to have the avro message sent to kafka
    @Bean
    KafkaProducerFactory<String, Object> avroSink(KafkaConfig kafkaConfig) {
        SchemaRegistryClient client =
                new CachedSchemaRegistryClient(kafkaConfig.getSchemaRegistryUrl(), 10);

        KafkaAvroSerializer kafkaAvroSerializer = new KafkaAvroSerializer(client);
        return new KafkaProducerFactory(kafkaConfig().getProducerPropsAvro(), kafkaConfig().getTopic(), new StringSerializer(), kafkaAvroSerializer);
    }
}
