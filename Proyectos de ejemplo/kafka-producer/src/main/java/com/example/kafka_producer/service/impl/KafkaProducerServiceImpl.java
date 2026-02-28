package com.example.kafka_producer.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafka_producer.config.KafkaProducerConfig;
import com.example.kafka_producer.dto.NotificacionDTO;
import com.example.kafka_producer.service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, NotificacionDTO> kafkaTemplate;

	@Override
	public void enviarNotificacion(NotificacionDTO notificacion) {

		notificacion.setId(System.nanoTime());
		notificacion.setFecha(Instant.now().toString());

		kafkaTemplate.send(KafkaProducerConfig.TOPIC, notificacion);
//		kafkaTemplate.send(KafkaProducerConfig.TOPIC, 1, "", notificacion);
	}
}
