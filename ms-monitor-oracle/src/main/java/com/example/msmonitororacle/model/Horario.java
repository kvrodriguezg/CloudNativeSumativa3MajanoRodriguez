package com.example.msmonitororacle.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Horario {
    private String idVehiculo;
    private String ubicacionAproximada;
    private LocalDateTime horaLlegadaEstimada;
}
