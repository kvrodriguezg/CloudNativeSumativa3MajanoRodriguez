package com.example.msprocesadorhorarios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horario {
    private String idVehiculo;
    private String ubicacionAproximada;
    private LocalDateTime horaLlegadaEstimada;
}
