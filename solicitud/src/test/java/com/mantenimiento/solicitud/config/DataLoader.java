package com.mantenimiento.solicitud.config;

import com.github.javafaker.Faker;
import com.mantenimiento.solicitud.model.Solicitud;
import com.mantenimiento.solicitud.repository.SolicitudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    CommandLineRunner initDatabase(SolicitudRepository repository) {
        return args -> {
            try {
                if (repository.count() == 0) {
                    Faker faker = new Faker(new Locale("es"));
                    
                    List<Solicitud> solicitudes = IntStream.rangeClosed(1, 20)
                        .mapToObj(i -> {
                            String tipo = faker.options().option("Mantenimiento", "Reparación", "Instalación");
                            return Solicitud.builder()
                                .tipo(tipo)
                                .descripcion("Solicitud de " + tipo + ": " + faker.lorem().sentence())
                                .fechaSolicitud(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)))
                                .estado(faker.options().option("Pendiente", "En Proceso", "Completada"))
                                .solicitante(faker.name().fullName())
                                .build();
                        })
                        .collect(Collectors.toList());
                    
                    repository.saveAll(solicitudes);
                    log.info("Se cargaron {} solicitudes de prueba", solicitudes.size());
                }
            } catch (Exception e) {
                log.error("Error al cargar datos de prueba: {}", e.getMessage());
            }
        };
    }
}