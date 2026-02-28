package com.example.msproductorubicaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsProductorUbicacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductorUbicacionesApplication.class, args);
	}
}
