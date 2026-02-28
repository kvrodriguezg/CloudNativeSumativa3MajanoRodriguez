package com.example.kafka_consumer.service;

import org.springframework.kafka.support.Acknowledgment;

import com.example.kafka_consumer.dto.NotificacionDTO;

public interface KafkaConsumerService {

	void consumirNotificacion(NotificacionDTO notificacion, Acknowledgment ack);
}
