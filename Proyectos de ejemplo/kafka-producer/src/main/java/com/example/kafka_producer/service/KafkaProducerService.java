package com.example.kafka_producer.service;

import com.example.kafka_producer.dto.NotificacionDTO;

public interface KafkaProducerService {

	void enviarNotificacion(NotificacionDTO notificacion);
}
