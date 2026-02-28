package com.example.kafka_consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import com.example.kafka_consumer.service.KafkaAdminListenerService;

@Service
public class KafkaAdminListenerServiceImpl implements KafkaAdminListenerService {

	@Autowired
	private KafkaListenerEndpointRegistry registry;

	@Override
	public void pausarListener(String id) {

		registry.getListenerContainer(id).pause();
	}

	@Override
	public void reanudarListener(String id) {

		registry.getListenerContainer(id).resume();
	}

	@Override
	public boolean obtenerEstadoListener(String id) {

		return registry.getListenerContainer(id).isPauseRequested();
	}
}
