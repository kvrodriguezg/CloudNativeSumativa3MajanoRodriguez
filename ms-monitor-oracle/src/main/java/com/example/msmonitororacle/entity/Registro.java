package com.example.msmonitororacle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "REGISTRO_VEHICULOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idVehiculo;
    private double latitud;
    private double longitud;
    private String ubicacionAproximada;
    private LocalDateTime timestampUbicacion;
    private LocalDateTime horaLlegadaEstimada;
    private LocalDateTime fechaRegistro;
    private String tipoRegistro; // indica si el registro es UBICACION o HORARIO
}
