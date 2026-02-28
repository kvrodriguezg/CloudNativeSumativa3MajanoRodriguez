package com.example.kafka_consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka_consumer.service.KafkaAdminListenerService;

@RestController
@RequestMapping("/kafka-listener")
public class KafkaAdminListenerController {

	@Autowired
	private KafkaAdminListenerService service;

	@PostMapping("/{id}/pausar")
	public String pausarListener(@PathVariable String id) {

		service.pausarListener(id);

		return "Listener " + id + " pausado";
	}

	@PostMapping("/{id}/reanudar")
	public String reanudarListener(@PathVariable String id) {

		service.reanudarListener(id);

		return "Listener " + id + " reanudado";
	}

	@GetMapping("/{id}/estado")
	public String obtenerEstadoListener(@PathVariable String id) {

		return "Listener " + id + " está " + (service.obtenerEstadoListener(id) ? "detenido" : "activo");
	}
}
