package com.example.kafka_producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.kafka_producer.dto.NotificacionDTO;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

	private static final String BOOTSTRAP_SERVERS = "localhost:29092,localhost:39092,localhost:49092";
	public static final String TOPIC = "Notificaciones";

	@Bean
	KafkaAdmin kafkaAdmin() {

		Map<String, Object> configs = new HashMap<>();
		configs.put("bootstrap.servers", BOOTSTRAP_SERVERS);

		return new KafkaAdmin(configs);
	}

	@Bean
	NewTopic topicNotificaciones() {

		Map<String, String> configs = new HashMap<>();
		configs.put("retention.ms", "43200000"); // 12 horas

		return new NewTopic(TOPIC, 3, (short) 2).configs(configs); // 3 particiones 2 replicas
	}

	@Bean
	ProducerFactory<String, NotificacionDTO> producerFactory() {

		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
				"org.apache.kafka.clients.producer.RoundRobinPartitioner");

		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	KafkaTemplate<String, NotificacionDTO> kafkaTemplate() {

		return new KafkaTemplate<>(producerFactory());
	}
}