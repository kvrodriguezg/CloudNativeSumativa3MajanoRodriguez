package com.example.kafka_consumer.service;

public interface KafkaAdminListenerService {

	void pausarListener(String id);

	void reanudarListener(String id);

	boolean obtenerEstadoListener(String id);
}
