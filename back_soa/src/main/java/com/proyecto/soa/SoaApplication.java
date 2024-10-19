package com.proyecto.soa;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class SoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoaApplication.class, args);
	}
	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		System.out.println("Zona horaria configurada: " + TimeZone.getDefault().getID());
	}

}
