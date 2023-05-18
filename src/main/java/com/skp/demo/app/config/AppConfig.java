package com.skp.demo.app.config;

import com.comcast.gateway.library.KafkaProducerFactory;
import com.comcast.gateway.library.config.KafkaProducerConfig;
import com.comcast.gateway.library.service.KafkaProducerService;
import com.comcast.gateway.library.service.impl.KafkaProducerServiceImpl;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

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
//    @Bean
//    KafkaProducerFactory<String, Object> avroSink(KafkaConfig kafkaConfig) {
//        SchemaRegistryClient client =
//                new CachedSchemaRegistryClient(kafkaConfig.getSchemaRegistryUrl(), 10);
//
//        KafkaAvroSerializer kafkaAvroSerializer = new KafkaAvroSerializer(client);
//        return new KafkaProducerFactory(kafkaConfig().getProducerPropsAvro(), kafkaConfig().getTopic(), new StringSerializer(), kafkaAvroSerializer);
//    }

    @Bean
    KafkaProducerConfig kafkaProducerConfig(){
        return new KafkaProducerConfig();
    }

    @Bean
    KafkaTemplate<String, Object> kafkaTemplateAvro(KafkaConfig kafkaConfig) {
        SchemaRegistryClient client =
                new CachedSchemaRegistryClient(kafkaConfig.getSchemaRegistryUrl(), 10);

        KafkaAvroSerializer kafkaAvroSerializer = new KafkaAvroSerializer(client);
        return kafkaProducerConfig().kafkaTemplateAvro();
    }

    @Bean
    KafkaTemplate<String, String> kafkaTemplate(KafkaConfig kafkaConfig) {
        SchemaRegistryClient client =
                new CachedSchemaRegistryClient(kafkaConfig.getSchemaRegistryUrl(), 10);

        KafkaAvroSerializer kafkaAvroSerializer = new KafkaAvroSerializer(client);
        return kafkaProducerConfig().kafkaTemplate();
    }


    @Bean
    KafkaProducerService kafkaProducerService() {
        return new KafkaProducerServiceImpl(kafkaTemplate(kafkaConfig()), kafkaTemplateAvro(kafkaConfig()), kafkaProducerConfig());
    }

}
