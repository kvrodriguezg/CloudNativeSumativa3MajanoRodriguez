package com.example.msproductorubicaciones.service;

import com.example.msproductorubicaciones.model.Ubicacion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UbicacionProducerService {

    private final KafkaTemplate<String, Ubicacion> kafkaTemplate;
    private static final String TOPIC = "ubicaciones_vehiculos";

    public void enviarUbicacion(Ubicacion ubicacion) {
        log.info("Produciendo ubicacion: {}", ubicacion);
        kafkaTemplate.send(TOPIC, ubicacion.getIdVehiculo(), ubicacion);
    }
}
