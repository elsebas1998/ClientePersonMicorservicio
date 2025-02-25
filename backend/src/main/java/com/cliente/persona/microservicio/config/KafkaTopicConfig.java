package com.cliente.persona.microservicio.config;

import com.cliente.persona.microservicio.Dto.ClienteInfoRequest;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    public static final String TOPIC_CLIENTE_REQUEST = "cliente-info-request";
    public static final String TOPIC_CLIENTE_RESPONSE = "cliente-info-response";

    @Bean
    public NewTopic clienteRequestTopic() {
        return TopicBuilder.name(TOPIC_CLIENTE_REQUEST)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic clienteResponseTopic() {
        return TopicBuilder.name(TOPIC_CLIENTE_RESPONSE)
                .partitions(3)
                .replicas(1)
                .build();
    }
    @Bean
    public ConsumerFactory<String, ClienteInfoRequest> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "mi-grupo-consumidor");

        JsonDeserializer<ClienteInfoRequest> deserializer = new JsonDeserializer<>(ClienteInfoRequest.class);
        deserializer.addTrustedPackages("*");

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        Map<String, Class<?>> typeMappings = new HashMap<>();

        typeMappings.put("com.cuenta.moviemiento.microservicio.Dto.ClienteInfoRequest",
                com.cliente.persona.microservicio.Dto.ClienteInfoRequest.class);

        typeMapper.setIdClassMapping(typeMappings);
        deserializer.setTypeMapper(typeMapper);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }
}