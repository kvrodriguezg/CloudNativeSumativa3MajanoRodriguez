package com.example.kafka_producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka_producer.dto.NotificacionDTO;
import com.example.kafka_producer.service.KafkaProducerService;

@RestController
public class ProducerController {

	@Autowired
	private KafkaProducerService producerService;

	@PostMapping("/notificaciones")
	public String crearNotificacion(@RequestBody() NotificacionDTO request) {

		producerService.enviarNotificacion(request);

		return "Notificación generada: " + request.toString();
	}
}
