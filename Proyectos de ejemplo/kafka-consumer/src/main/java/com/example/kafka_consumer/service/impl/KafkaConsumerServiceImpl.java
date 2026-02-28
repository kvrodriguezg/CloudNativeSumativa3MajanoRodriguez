package com.example.kafka_consumer.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.example.kafka_consumer.config.KafkaConsumerConfig;
import com.example.kafka_consumer.dto.NotificacionDTO;
import com.example.kafka_consumer.service.KafkaConsumerService;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	@Override
	@KafkaListener(id = "notificacionListener", topics = KafkaConsumerConfig.TOPIC,
			groupId = KafkaConsumerConfig.CONSUMER_GROUP_ID)
	public void consumirNotificacion(NotificacionDTO notificacion, Acknowledgment ack) {

		try {

			System.out.println("Mensaje consumido: " + notificacion.toString());

			Thread.sleep(5000);

			ack.acknowledge();

			System.out.println("Acknowledge realizado: " + notificacion.toString());

		} catch (Exception e) {

			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
}
