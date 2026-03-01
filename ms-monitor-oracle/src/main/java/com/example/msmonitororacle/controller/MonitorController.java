package com.example.msmonitororacle.controller;

import com.example.msmonitororacle.entity.Registro;
import com.example.msmonitororacle.repository.RegistroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registros")
@RequiredArgsConstructor
public class MonitorController {

    private final RegistroRepository registroRepository;

    @GetMapping
    public List<Registro> getAllRegistros() {
        return registroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registro> getRegistroById(@PathVariable Long id) {
        return registroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Registro createRegistro(@RequestBody Registro registro) {
        registro.setFechaRegistro(LocalDateTime.now());
        return registroRepository.save(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registro> updateRegistro(@PathVariable Long id, @RequestBody Registro detalles) {
        return registroRepository.findById(id).map(registro -> {
            registro.setIdVehiculo(detalles.getIdVehiculo());
            registro.setLatitud(detalles.getLatitud());
            registro.setLongitud(detalles.getLongitud());
            registro.setUbicacionAproximada(detalles.getUbicacionAproximada());
            registro.setHoraLlegadaEstimada(detalles.getHoraLlegadaEstimada());
            registro.setTipoRegistro(detalles.getTipoRegistro());
            return ResponseEntity.ok(registroRepository.save(registro));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        if (registroRepository.existsById(id)) {
            registroRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/resumen-diario")
    public ResponseEntity<?> getResumenDiario() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);

        List<Registro> registrosHoy = registroRepository.findByFechaRegistroBetween(startOfDay, endOfDay);

        //Map agrupando por vehiculo
        Map<String, List<Registro>> resumenPorVehiculo = registrosHoy.stream()
                .filter(r -> r.getIdVehiculo() != null)
                .collect(Collectors.groupingBy(Registro::getIdVehiculo));

        return ResponseEntity.ok(resumenPorVehiculo);
    }
}
