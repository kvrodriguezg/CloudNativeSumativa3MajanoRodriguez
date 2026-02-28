package com.example.msmonitororacle.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Ubicacion {
    private String idVehiculo;
    private double latitud;
    private double longitud;
    private LocalDateTime timestamp;
}
