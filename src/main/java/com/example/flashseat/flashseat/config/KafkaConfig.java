package com.example.flashseat.flashseat.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    private String bootstrapServers() {
        return System.getenv()
                .getOrDefault("KAFKA_BOOTSTRAP_SERVERS", "localhost:9092");
    }

    private void addSecurityConfig(Map<String, Object> config) {

        String securityProtocol =
                System.getenv().getOrDefault("KAFKA_SECURITY_PROTOCOL", "PLAINTEXT");

        config.put("security.protocol", securityProtocol);

        String saslMechanism =
                System.getenv().getOrDefault("KAFKA_SASL_MECHANISM", "");

        String username =
                System.getenv().getOrDefault("KAFKA_USERNAME", "");

        String password =
                System.getenv().getOrDefault("KAFKA_PASSWORD", "");

        if (!saslMechanism.isBlank()) {
            config.put("sasl.mechanism", saslMechanism);
        }

        if (!username.isBlank() && !password.isBlank()) {

            config.put(
                    "sasl.jaas.config",
                    "org.apache.kafka.common.security.scram.ScramLoginModule required "
                            + "username=\"" + username + "\" "
                            + "password=\"" + password + "\";"
            );
        }
    }

    // =========================
    // PRODUCER
    // =========================

    @Bean
    public ProducerFactory<String, String> producerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers()
        );

        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        addSecurityConfig(config);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // =========================
    // CONSUMER
    // =========================

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers()
        );

        config.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "flashseat-group"
        );

        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );

        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );

        config.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest"
        );

        addSecurityConfig(config);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}