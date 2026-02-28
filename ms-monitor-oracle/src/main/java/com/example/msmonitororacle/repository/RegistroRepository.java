package com.example.msmonitororacle.repository;

import com.example.msmonitororacle.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    List<Registro> findByFechaRegistroBetween(LocalDateTime start, LocalDateTime end);
}
