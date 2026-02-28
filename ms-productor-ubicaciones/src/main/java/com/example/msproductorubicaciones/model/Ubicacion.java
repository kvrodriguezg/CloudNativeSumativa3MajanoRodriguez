package com.example.msproductorubicaciones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion {
    private String idVehiculo;
    private double latitud;
    private double longitud;
    private LocalDateTime timestamp;
}
