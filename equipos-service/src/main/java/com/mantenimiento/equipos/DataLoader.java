package com.mantenimiento.equipos;

import com.mantenimiento.equipos.model.Equipo;
import com.mantenimiento.equipos.repository.EquipoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Usando bucle for en lugar de IntStream
        for (int i = 0; i < 100; i++) {
            Equipo equipo = Equipo.builder()
                    .id((long) (i + 1)) // Asignar un ID único
                    .nombre(faker.commerce().productName())
                    .ubicacion(faker.address().fullAddress())
                    .estado(faker.options().option("Operativo", "En Mantenimiento", "Fuera de Servicio"))
                    .modelo(faker.commerce().material())
                    .numeroSerie(faker.number().digits(10))
                    .fechaInstalacion(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)))
                    .ultimoMantenimiento(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)))
                    .build();
            
            equipoRepository.save(equipo);
        }
    }
}