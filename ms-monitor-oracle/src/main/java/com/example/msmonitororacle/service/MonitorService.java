package com.example.msmonitororacle.service;

import com.example.msmonitororacle.entity.Registro;
import com.example.msmonitororacle.model.Horario;
import com.example.msmonitororacle.model.Ubicacion;
import com.example.msmonitororacle.repository.RegistroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitorService {

    private final RegistroRepository registroRepository;

    @KafkaListener(topics = "ubicaciones_vehiculos", groupId = "monitor-group", containerFactory = "ubicacionKafkaListenerContainerFactory")
    public void consumirUbicacion(Ubicacion ubicacion) {
        log.info("Monitor recibio ubicacion: {}", ubicacion);

        Registro registro = new Registro();
        registro.setIdVehiculo(ubicacion.getIdVehiculo());
        registro.setLatitud(ubicacion.getLatitud());
        registro.setLongitud(ubicacion.getLongitud());
        registro.setTimestampUbicacion(ubicacion.getTimestamp());
        registro.setFechaRegistro(LocalDateTime.now());
        registro.setTipoRegistro("UBICACION");

        registroRepository.save(registro);
    }

    @KafkaListener(topics = "horarios", groupId = "monitor-group", containerFactory = "horarioKafkaListenerContainerFactory")
    public void consumirHorario(Horario horario) {
        log.info("Monitor recibio horario: {}", horario);

        Registro registro = new Registro();
        registro.setIdVehiculo(horario.getIdVehiculo());
        registro.setUbicacionAproximada(horario.getUbicacionAproximada());
        registro.setHoraLlegadaEstimada(horario.getHoraLlegadaEstimada());
        registro.setFechaRegistro(LocalDateTime.now());
        registro.setTipoRegistro("HORARIO");

        registroRepository.save(registro);
    }
}
