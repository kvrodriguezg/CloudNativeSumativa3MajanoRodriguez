package com.example.kafka_consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.kafka_consumer.dto.NotificacionDTO;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	private static final String BOOTSTRAP_SERVERS = "localhost:29092,localhost:39092,localhost:49092";
	public static final String TOPIC = "Notificaciones";
	public static final String CONSUMER_GROUP_ID = "consumidores1";

	@Bean
	KafkaAdmin kafkaAdmin() {

		Map<String, Object> configs = new HashMap<>();
		configs.put("bootstrap.servers", BOOTSTRAP_SERVERS);

		return new KafkaAdmin(configs);
	}

	@Bean
	ConsumerFactory<String, NotificacionDTO> consumerFactory() {

		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_ID);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");// latest-earliest

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
				new JsonDeserializer<>(NotificacionDTO.class, false));
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, NotificacionDTO> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, NotificacionDTO> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

		return factory;
	}
}
