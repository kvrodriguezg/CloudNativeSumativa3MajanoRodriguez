package com.example.msproductorubicaciones.scheduler;

import com.example.msproductorubicaciones.model.Ubicacion;
import com.example.msproductorubicaciones.service.UbicacionProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class UbicacionScheduler {

    private final UbicacionProducerService ubicacionProducerService;
    private final Random random = new Random();
    private final List<String> vehiculos = Arrays.asList("VEH-001", "VEH-002", "VEH-003", "VEH-004", "VEH-005");

    @Scheduled(fixedRate = 5000)
    public void generarYEnviarUbicacion() {
        String vehiculo = vehiculos.get(random.nextInt(vehiculos.size()));
        double latitud = -33.4372 + (random.nextDouble() - 0.5) * 0.1; // Coordenadas aleatorias cerca de Santiago
        double longitud = -70.6506 + (random.nextDouble() - 0.5) * 0.1;

        Ubicacion ubicacion = new Ubicacion(vehiculo, latitud, longitud, LocalDateTime.now());
        ubicacionProducerService.enviarUbicacion(ubicacion);
    }
}
