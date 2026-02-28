package com.example.msprocesadorhorarios.service;

import com.example.msprocesadorhorarios.model.Horario;
import com.example.msprocesadorhorarios.model.Ubicacion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcesadorHorariosService {

    private final KafkaTemplate<String, Horario> kafkaTemplate;
    private static final String TOPICO_HORARIOS = "horarios";

    @KafkaListener(topics = "ubicaciones_vehiculos", groupId = "procesador-horarios-group")
    public void consumirUbicacionYProducirHorario(Ubicacion ubicacion) {
        log.info("Ubicacion consumida: {}", ubicacion);

        // Logica mock para "actualizar horarios basado en ubicaciones"
        String ubicacionAprox = determinarArea(ubicacion.getLatitud(), ubicacion.getLongitud());
        LocalDateTime horaEstimada = ubicacion.getTimestamp().plusMinutes(15);

        Horario horarioActualizado = new Horario(
                ubicacion.getIdVehiculo(),
                ubicacionAprox,
                horaEstimada);

        log.info("Produciendo horario actualizado: {}", horarioActualizado);
        kafkaTemplate.send(TOPICO_HORARIOS, horarioActualizado.getIdVehiculo(), horarioActualizado);
    }

    private String determinarArea(double lat, double lon) {
        // Logica simple basada en cordenadas para asignar sector
        if (lat < -33.43)
            return "Sector Norte";
        if (lat > -33.43)
            return "Sector Sur";
        return "Centro";
    }
}
