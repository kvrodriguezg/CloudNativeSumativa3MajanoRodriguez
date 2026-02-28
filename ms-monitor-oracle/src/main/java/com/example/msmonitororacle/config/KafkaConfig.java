package com.example.msmonitororacle.config;

import com.example.msmonitororacle.model.Horario;
import com.example.msmonitororacle.model.Ubicacion;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, Ubicacion> ubicacionConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "monitor-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(Ubicacion.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Ubicacion> ubicacionKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Ubicacion> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ubicacionConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Horario> horarioConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "monitor-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(Horario.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Horario> horarioKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Horario> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(horarioConsumerFactory());
        return factory;
    }
}
