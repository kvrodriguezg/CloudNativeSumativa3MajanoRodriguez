package com.example.msmonitororacle.scheduler;

import com.example.msmonitororacle.entity.Registro;
import com.example.msmonitororacle.repository.RegistroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DailySummaryScheduler {

    private final RegistroRepository registroRepository;

    @Scheduled(cron = "20 37 00 * * *")
    public void generateDailySummary() {
        log.info("--- GENERANDO RESUMEN DIARIO DE UBICACIONES Y HORARIOS ---");

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);

        List<Registro> registrosHoy = registroRepository.findByFechaRegistroBetween(startOfDay, endOfDay);

        Map<String, List<Registro>> resumenPorVehiculo = registrosHoy.stream()
                .filter(r -> r.getIdVehiculo() != null)
                .collect(Collectors.groupingBy(Registro::getIdVehiculo));

        resumenPorVehiculo.forEach((vehiculo, registros) -> {
            log.info("Vehiculo: {}", vehiculo);
            log.info("Total registros procesados: {}", registros.size());
            registros.stream()
                    .filter(r -> r.getTipoRegistro().equals("HORARIO"))
                    .forEach(r -> log.info(" -> Llego a {} con hora estimada {}",
                            r.getUbicacionAproximada(), r.getHoraLlegadaEstimada()));
        });

        log.info("--- FIN DEL RESUMEN DIARIO ---");
    }
}
