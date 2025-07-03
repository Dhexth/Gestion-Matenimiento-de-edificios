package com.mantenimiento.solicitud.config;

import com.github.javafaker.Faker;
import com.mantenimiento.solicitud.model.Solicitud;
import com.mantenimiento.solicitud.repository.SolicitudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(SolicitudRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));
                
                List<Solicitud> solicitudes = IntStream.rangeClosed(1, 20)
                    .mapToObj(i -> {
                        Solicitud solicitud = new Solicitud();
                        solicitud.setTipo(faker.options().option("Mantenimiento", "Reparación", "Instalación"));
                        solicitud.setDescripcion("Solicitud de " + solicitud.getTipo() + ": " + faker.lorem().sentence());
                        solicitud.setFechaSolicitud(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)));
                        solicitud.setEstado(faker.options().option("Pendiente", "En Proceso", "Completada"));
                        solicitud.setSolicitante(faker.name().fullName());
                        return solicitud;
                    })
                    .collect(Collectors.toList());
                
                repository.saveAll(solicitudes);
                System.out.println("Se cargaron " + solicitudes.size() + " solicitudes de prueba.");
            }
        };
    }
}